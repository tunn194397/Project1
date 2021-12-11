package com.zetcode.view;

import com.zetcode.controller.MouseController;

import org.json.simple.JSONObject;

import com.zetcode.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class Board extends JPanel implements ActionListener {
    // Các biến toàn cục trong Board
    
    public final int B_WIDTH = 1200;
    public final int B_HEIGHT = 600;
    private final int DOT_SIZE = 10;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    //Các biến chứa các giá trị Validate
    public float validMaxSizeOfRoom = (float) 0.7;// Đây là giá trị vadidate cho kích cỡ lớn nhất của các phòng có thể có trong map
    public int status = 0; // 0 la binh thuong, 1 la ve duong cho AGV, 3 la resize
    public final int MAX_ROOM_QUANTITY = 10;
    public final int MAX_AGV_QUANTITY = 6;
    public final int MAX_PERSON_QUANTITY = 10;
    public final int MAX_GURNEY_QUANTITY = 10;
    public final int MAX_LIFT_QUANTITY = 8;
    public final int MAX_PORT_QUANTITY = 8;

    //Controller của chuột, cái này không đẩy được sang class UI vì bị thay đổi liên tục trong repaint()
    MouseController ma;

    //Các hướng của mainAGV
    public boolean leftDirection = false;
    public boolean rightDirection = true;
    public boolean upDirection = false;
    public boolean downDirection = false;


    // Các Facilities và Node trong Board
    public static final AGV mainAGV = new AGV(120,120);
    public Node[][] nodeArray = new Node[B_WIDTH/30][B_HEIGHT/30];
    public Vector<Node> nodeIsLineArray = new Vector<>();
    public Vector<Room> roomArray = new Vector<>();
    public Vector<AGV> agvArray = new Vector<>();
    public Vector<Gurney> gurneyArray = new Vector<>();
    public Vector<Lift> liftArray = new Vector<>();
    public Vector<Port> portArray = new Vector<>();
    public Vector<Person> personArray = new Vector<>();
    public Facility collector = new Facility();
    public Map map = new Map();
    public Map openMap = new Map();

    public Vector<Facility> facilities = new Vector<>();
    public Node firstNode = new Node(), lastNode = new Node();

    // Biến timer
    public Timer timer;

    public Board() {
        setBackground(Color.white);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        constructData();


//        addKeyListener(new TAdapter());
        ma = new MouseController(this);
        addMouseController(ma);
    }
    public void addMouseController(MouseController ma ) {
        addMouseListener(ma);
        addMouseMotionListener(ma);
        addMouseWheelListener(ma);
    }

//    private void loadImages() {
//
//    }
    public void initGame() {
        timer = new Timer(DELAY, this);
        timer.restart();
    }

    public void pauseGame() {
        if (this.timer != null) timer.stop();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Ve cac duong ke
//        for (int i = 0; i < personArray.length; i ++) {
//            personArray[i].personGraphic(g);
//        }
//        for (int i = 0; i < agvArray.length; i ++) {
//            agvArray[i].agvGraphic(g);
//        }
//        for (int i = 0; i < gurneyArray.length; i ++) {
//            gurneyArray[i].gurneyGraphic(g);
//        }


        //Ve phan AGV
        ma.updateController();
        doDrawing(g);
    }
    public void doDrawing(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        g.setColor(Color.lightGray);
        for (int i = 0; i < B_WIDTH/30; i ++) {
            for (int j = 0; j < B_HEIGHT/30 ; j ++ ) {
                nodeArray[i][j].draw(g);
            }
        }
        for (int i = 0; i < B_HEIGHT; i ++) {
            if (i % 30 == 0) {
                g.drawLine(0,i,B_WIDTH,i);
            }
        }
        for (int i = 0; i < B_WIDTH; i ++) {
            if (i % 30 == 0) {
                g.drawLine(i,0,i,B_HEIGHT);
            }
        }
        mainAGV.draw(g);
        // Ve 4 cong vao ra
        for (Facility facility: facilities){
            facility.draw(g);
        }
        if (checkRoomOverTotalSize()){
            JOptionPane.showMessageDialog(this,"Total size of room cannot over " + validMaxSizeOfRoom*100 + "% size of room","Warning",JOptionPane.WARNING_MESSAGE);
        }
        if (!collector.ID.equals("Null")) {
            g.setColor(Color.gray);
            g.fillRect(collector.x, collector.y,collector.size_x,collector.size_y);
        };
        doRound();
    }

    private void move() {
        if (mainAGV.checkCollisionWithNode(nodeArray)) {
            moveWhenCollision();
        }
        else {
            for (Facility facility : facilities) {
                if (mainAGV.checkCollision(facility)) {
                    moveWhenCollision();
                }
            }
            if (mainAGV.y < 0) {
                mainAGV.y += DOT_SIZE;
                upDirection = false;
                downDirection = false;
                leftDirection = mainAGV.x > 600;
                rightDirection = mainAGV.x <= 600;
                mainAGV.switchSide();
            }
            else if (mainAGV.y > 570){
                mainAGV.y -= DOT_SIZE;
                upDirection = false;
                downDirection = false;
                leftDirection = mainAGV.x > 600;
                rightDirection = mainAGV.x <= 600;
                mainAGV.switchSide();
            }
            else if (mainAGV.x < 10){
                mainAGV.x += DOT_SIZE;
                leftDirection = false;
                rightDirection = false;
                upDirection = mainAGV.y > 300;
                downDirection = mainAGV.y <= 300;
                mainAGV.switchSide();
            }
            else if (mainAGV.x > 1170) {
                mainAGV.x -= DOT_SIZE;
                leftDirection = false;
                rightDirection = false;
                upDirection = mainAGV.y > 300;
                downDirection = mainAGV.y <= 300;
                mainAGV.switchSide();
            }
            else {
                if (leftDirection) {mainAGV.x -= DOT_SIZE;}
                if (rightDirection) {mainAGV.x += DOT_SIZE;}
                if (upDirection) {mainAGV.y -= DOT_SIZE;}
                if (downDirection) {mainAGV.y += DOT_SIZE;}
            }
        }
    }
    public void moveWhenCollision(){
//        if (leftDirection) {
//            mainAGV.x += DOT_SIZE;
//            leftDirection = false;
//            upDirection = mainAGV.y > 300;
//            downDirection = mainAGV.y <= 300;
//            mainAGV.switchSide();
//        } else if (rightDirection) {
//            mainAGV.x -= DOT_SIZE;
//            rightDirection = false;
//            upDirection = mainAGV.y > 300;
//            downDirection = mainAGV.y <= 300;
//            mainAGV.switchSide();
//        } else if (upDirection) {
//            mainAGV.y += DOT_SIZE;
//            upDirection = false;
//            leftDirection = mainAGV.x > 600;
//            rightDirection = mainAGV.x <= 600;
//            mainAGV.switchSide();
//        } else if (downDirection) {
//            mainAGV.y -= DOT_SIZE;
//            downDirection = false;
//            leftDirection = mainAGV.x > 600;
//            rightDirection = mainAGV.x <= 600;
//            mainAGV.switchSide();
//        }
        pauseGame();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
    private void doRound() {
        int ex = (collector.x % 30 <= 15)? collector.x - ( collector.x % 30): collector.x + 30 - collector.x % 30;
        int ey = (collector.y % 30 <= 15)? collector.y - ( collector.y % 30): collector.y + 30 - collector.y % 30;
        for (Facility facility: facilities) {
            if (facility.ID.equals(collector.ID)) facility.update(ex,ey);
        }
        collector.update(ex,ey);
        repaint();
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean checkRoomOverTotalSize(){
        int totalArea = 0;
        for (Room room: roomArray){
            totalArea += room.size_x*room.size_y/900;
        }
        if ( totalArea > validMaxSizeOfRoom*800) {
            collector.updateSize(-15);
            repaint();
            return true;
        }
        return false;
    }
    public void saveGame(){
       /* for (Port port: portArray){
            PortJs portJs = new PortJs(port);
            map.add(portJs);
        }
        for(Lift lift: liftArray){
            LiftJs liftJs = new LiftJs(lift);
            map.add(liftJs);
        }
        for(Room room: roomArray){
            RoomJs roomJs= new RoomJs(room) ;
            map.add(roomJs);
        }*/
        for(Facility facility: facilities){
            FacilityJS facilityJS = new FacilityJS(facility);
            map.add(facilityJS);
        }
        map.SaveMap();
        System.out.println(map.size());
    }

    public void loadGame(String nameFile){
       for(Facility facility: map.LoadMap(nameFile) ){
           facility.draw(getGraphics());
       }
    }
    private void constructData() {
        portArray.add(new Port(1,1));
        portArray.add(new Port(1110,1));
        portArray.add(new Port(1,540));
        portArray.add(new Port(1110,540));


        liftArray.add(new Lift(1,240));
        liftArray.add(new Lift(1110,240));
        liftArray.add(new Lift(1,300));
        liftArray.add(new Lift(1110,300));
//
//        roomArray.add(new Room(150,60));
//        roomArray.add(new Room(480,60));
//        roomArray.add(new Room(150,360));
//        roomArray.add(new Room(480,360));
//        roomArray.add(new Room(810,60));
//        roomArray.add(new Room(810,360));

        for (int i = 0; i < B_WIDTH/30; i ++) {
            for (int j = 0; j < B_HEIGHT/30 ; j ++ ) {
                nodeArray[i][j] = new Node(i*30, j*30);
                System.out.println(nodeArray[i][j].coordinate_x + " " + nodeArray[i][j].coordinate_y);
            }
        }
        updateFacilities();
    }
    public void updateFacilities(){
//        facilities.addAll(Arrays.asList(room.doorArray));
        for (Room room : roomArray) {
            if (room.checkBelongToFacilities(facilities) == 0) facilities.add(room);
        }
        for (Lift lift : liftArray) {
            if (lift.checkBelongToFacilities(facilities) == 0) facilities.add(lift);
        }
        for (Port port : portArray) {
            if (port.checkBelongToFacilities(facilities) == 0) facilities.add(port);
        }
        for (Facility facility: facilities) {
            System.out.println(facility.ID);
        }
    }
    public void updateNode() {
        for (Node[] nodes: nodeArray) {
            for (Node node: nodes){
                if (node.direction != 0) nodeIsLineArray.add(node);
            }
        }
        updateLineGraph();
    }
    public void updateLineGraph() {
        for (int i = 0; i < nodeIsLineArray.size() - 1; i ++) {
            if (nodeIsLineArray.get(i).coordinate_x == nodeIsLineArray.get(i+1).coordinate_x) {
                if (nodeIsLineArray.get(i).coordinate_y > nodeIsLineArray.get(i+1).coordinate_y) {
                    nodeIsLineArray.get(i).setDown(nodeIsLineArray.get(i+1));
                    nodeIsLineArray.get(i+1).setUp(nodeIsLineArray.get(i));
                }
                else {
                    nodeIsLineArray.get(i).setUp(nodeIsLineArray.get(i+1));
                    nodeIsLineArray.get(i+1).setUp(nodeIsLineArray.get(i));
                }
            }
            else {
                if (nodeIsLineArray.get(i).coordinate_y == nodeIsLineArray.get(i+1).coordinate_y) {
                    if (nodeIsLineArray.get(i).coordinate_x > nodeIsLineArray.get(i+1).coordinate_x) {
                        nodeIsLineArray.get(i).setRight(nodeIsLineArray.get(i+1));
                        nodeIsLineArray.get(i+1).setLeft(nodeIsLineArray.get(i));
                    }
                    else {
                        nodeIsLineArray.get(i).setLeft(nodeIsLineArray.get(i+1));
                        nodeIsLineArray.get(i+1).setRight(nodeIsLineArray.get(i));
                    }
                }
            };
            nodeIsLineArray.get(i).setEdge();
        }
        System.out.println("Updating line graph");
    }
}


