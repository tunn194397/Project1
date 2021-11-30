package com.zetcode.view;

import com.zetcode.controller.MouseController;

import org.json.simple.JSONObject;

import com.zetcode.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;

public class Board extends JPanel implements ActionListener {
    // Các biến toàn cục trong Board
    public final int B_WIDTH = 1200;
    public final int B_HEIGHT = 600;
    private final int DOT_SIZE = 10;
    private final int MAX_PERSON = 10;
    private final int MAX_AGV = 10;
    private final int RAND_POS = 29;
    private final int DELAY = 140;
    public float validMaxSizeOfRoom = (float) 0.7;// Đây là giá trị vadidate cho kích cỡ lớn nhất của các phòng có thể có trong map
    public int status = 0; // 0 la binh thuong, 1 la ve duong cho AGV

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
    public Room[] roomArray = new Room[8];
    public AGV[] agvArray = new AGV[6];
    public Gurney[] gurneyArray = new Gurney[6];
    public Lift[] liftArray = new Lift[4];
    public Port[] portArray = new Port[4];
    public Person[] personArray = new Person[10];
    public Facility collector = new Facility();
    public Facility[] facilities = new Facility[30];
    public Map map = new Map();

    // Biến timer
    public Timer timer;

    public Board() {
        setBackground(Color.white);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        constructData();
//        loadImages();
//        initGame();

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
        timer.stop();
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
        for (int i = 0; i < B_HEIGHT; i ++) {
            if (i % 30 == 1) {
                g.drawLine(0,i,B_WIDTH,i);
            }
        }
        for (int i = 0; i < B_WIDTH; i ++) {
            if (i % 30 == 1) {
                g.drawLine(i,0,i,B_HEIGHT);
            }
        }
        for (int i = 0; i < B_WIDTH/30; i ++) {
            for (int j = 0; j < B_HEIGHT/30 ; j ++ ) {
                nodeArray[i][j].draw(g);
            }
        }
        mainAGV.draw(g);
        // Ve 4 cong vao ra
        for (Port port : portArray) {
            port.draw(g);
        }
        for (Lift lift : liftArray) {
            lift.draw(g);
        }
        for (Room room : roomArray) {
            room.draw(g);
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
        if (leftDirection) {
            mainAGV.x += DOT_SIZE;
            leftDirection = false;
            upDirection = mainAGV.y > 300;
            downDirection = mainAGV.y <= 300;
            mainAGV.switchSide();
        } else if (rightDirection) {
            mainAGV.x -= DOT_SIZE;
            rightDirection = false;
            upDirection = mainAGV.y > 300;
            downDirection = mainAGV.y <= 300;
            mainAGV.switchSide();
        } else if (upDirection) {
            mainAGV.y += DOT_SIZE;
            upDirection = false;
            leftDirection = mainAGV.x > 600;
            rightDirection = mainAGV.x <= 600;
            mainAGV.switchSide();
        } else if (downDirection) {
            mainAGV.y -= DOT_SIZE;
            downDirection = false;
            leftDirection = mainAGV.x > 600;
            rightDirection = mainAGV.x <= 600;
            mainAGV.switchSide();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        doRound();
    }
    private void doRound() {
        int ex = (collector.x % 30 <= 15)? collector.x - ( collector.x % 30): collector.x + 30 - collector.x % 30;
        int ey = (collector.y % 30 <= 15)? collector.y - ( collector.y % 30): collector.y + 30 - collector.y % 30;
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
        for (Port port: portArray){
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
        }
        System.out.println("Save Game");
        map.SaveMap();
    }

    private void constructData() {
        Port p1 = new Port(1,1);
        Port p2 = new Port(1110,1);
        Port p3 = new Port(1,540);
        Port p4 = new Port(1110,540);
        portArray = new Port[]{p1,p2,p3,p4};
       
        
        Lift l1 = new Lift(1,240);
        Lift l2 = new Lift(1110,240);
        Lift l3 = new Lift(1,300);
        Lift l4 = new Lift(1110,300);
        liftArray = new Lift[]{l1,l2,l3,l4};
      


        Room r1 = new Room(150,60);
        Room r2 = new Room(480,60);
        Room r3 = new Room(150,360);
        Room r4 = new Room(480,360);
        Room r5 = new Room(810,60);
        Room r6 = new Room(810,360);
        roomArray = new Room[]{r1, r2, r3, r4, r5, r6};

        facilities = new Facility[]{p1,p2,p3,p4,r1,r2,r3,r4,r5,r6,l1,l2,l3,l4,
                r1.doorArray[0],r1.doorArray[1], r1.doorArray[2],r1.doorArray[3],
                r2.doorArray[0],r2.doorArray[1], r2.doorArray[2],r2.doorArray[3],
                r2.doorArray[0],r2.doorArray[1], r2.doorArray[2],r2.doorArray[3],
                r2.doorArray[0],r2.doorArray[1], r2.doorArray[2],r2.doorArray[3], };
        for (int i = 0; i < B_WIDTH/30; i ++) {
            for (int j = 0; j < B_HEIGHT/30 ; j ++ ) {
                nodeArray[i][j] = new Node(i*30+2, j*30+2);
            }
        }
    }
   

}


