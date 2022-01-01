package com.zetcode.view;

import com.zetcode.algorithm.ReadInput;
import com.zetcode.controller.MouseController;
import com.zetcode.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Board extends JPanel implements ActionListener {
    // Các biến toàn cục trong Board
    
    public final int B_WIDTH = 1200;
    public final int B_HEIGHT = 600;
    private final int DOT_SIZE = 10;
    private final int RAND_POS = 29;
    private final int DELAY = 100;

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
    public AGV mainAGV;
    public Node[][] nodeArray = new Node[B_WIDTH/30][B_HEIGHT/30];
    public ArrayList<Node> lineArray = new ArrayList<>();
    public ArrayList<Room> roomArray = new ArrayList<>();
    public ArrayList<AGV> agvArray = new ArrayList<>();
    public ArrayList<Gurney> gurneyArray = new ArrayList<>();
    public ArrayList<Lift> liftArray = new ArrayList<>();
    public ArrayList<Port> portArray = new ArrayList<>();
    public ArrayList<Person> personArray = new ArrayList<>();

    public Facility collector = new Facility();
    public Map map = new Map();
    public Map openMap = new Map();

    public Vector<Facility> facilities = new Vector<>();

    public ReadInput readInput = new ReadInput();
    public String[] doorCordinateX;
    public String[] doorCordinateY;
    public int numOfDoors;


    // Biến timer
    public Timer timer;

    public Board() {
        setBackground(Color.white);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setUpNode();
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
        for (AGV agv : agvArray) {
            agv.draw(g);
        }
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

        // Ve quy dao agent
        g.setColor(Color.red);
        readInput.drawRoute(g);
    }

    private void move() {
        mainAGV.move();
        for (AGV agv : agvArray) {
            agv.move();
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
    public void setUpNode() {
        for (int i = 0; i < B_WIDTH/30; i ++) {
            for (int j = 0; j < B_HEIGHT/30 ; j ++ ) {
                nodeArray[i][j] = new Node(i*30, j*30);
            }
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

        updateFacilities();

        mainAGV = new AGV(120,120,nodeArray);
        AGV agv1 = new AGV(210,60, nodeArray);
        AGV agv2 = new AGV(210,15, nodeArray);
        AGV agv3 = new AGV(420,450, nodeArray);
        AGV agv4 = new AGV(600,300, nodeArray);
        agvArray.add(agv1);
        agvArray.add(agv2);
        agvArray.add(agv3);
        agvArray.add(agv4);

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
                if (node.isLine) lineArray.add(node);
            }
        }
        updateLineGraph();
    }
    public void updateLineGraph() {
        for (int i = 0; i < lineArray.size() - 1; i ++) {
            if (lineArray.get(i).coordinate_x == lineArray.get(i+1).coordinate_x) {
                if (lineArray.get(i).coordinate_y == lineArray.get(i+1).coordinate_y + 1) {
                    lineArray.get(i).setUp(lineArray.get(i+1));
                }
                else {
                    if (lineArray.get(i).coordinate_y == lineArray.get(i+1).coordinate_y - 1) {
                        lineArray.get(i).setDown(lineArray.get(i+1));
                    }
                }
            }
            else {
                if (lineArray.get(i).coordinate_y == lineArray.get(i+1).coordinate_y) {
                    if (lineArray.get(i).coordinate_x ==  lineArray.get(i+1).coordinate_x + 1) {
                        lineArray.get(i).setLeft(lineArray.get(i+1));
                    }
                    else {
                        if (lineArray.get(i).coordinate_x ==  lineArray.get(i+1).coordinate_x - 1) {
                            lineArray.get(i).setRight(lineArray.get(i+1));
                        }
                    }
                }
            };
        }

        System.out.println("Updating line graph");
        System.out.println("Line array gom " + lineArray.size() + " phan tu");
        for (Node node : lineArray) {
            System.out.print("["+ node.coordinate_x+ "; " + node.coordinate_y+ "]");
            if (node.Up != null) System.out.print("--> "+ "["+ node.Up.coordinate_x+ "; " + node.Up.coordinate_y+ "]");
            if (node.Down != null) System.out.print("--> "+ "["+ node.Down.coordinate_x+ "; " + node.Down.coordinate_y+ "]");
            if (node.Right != null) System.out.print("--> "+ "["+ node.Right.coordinate_x+ "; " + node.Right.coordinate_y+ "]");
            if (node.Left != null) System.out.print("--> "+ "["+ node.Left.coordinate_x+ "; " + node.Left.coordinate_y+ "]");
            System.out.println("");
        }
    }

    public void printResult() throws IOException {
        numOfDoors = 0;
        doorCordinateX = new String[100];
        doorCordinateY = new String[100];
        File file = new File("src/com/zetcode/algorithm/testCase.inp");
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        String numberOfFacilities = String.valueOf(facilities.size());
        outputStreamWriter.write("100");  //Số lượng đường đi
        outputStreamWriter.write("\n");

        for (Room room : roomArray) {
            for (Door door : room.doorArray) {
                doorCordinateX[numOfDoors] = String.valueOf(door.getX() + 15);
                if (numOfDoors % 4 == 0 || numOfDoors % 4 == 1) {
                    doorCordinateY[numOfDoors] = String.valueOf(door.getY() - 6);  // 2 cửa trên có tọa độ y - 6
                } else {
                    doorCordinateY[numOfDoors] = String.valueOf(door.getY() + 16); // 2 cửa dưới có tọa độ y + 6
                }
                numOfDoors++;
            }
        }

        for (Port port : portArray) {
            if (port.getX() > 6) {
                doorCordinateX[numOfDoors] = String.valueOf(port.getX() - 6);
                doorCordinateY[numOfDoors] = String.valueOf(port.getY() + 30);
                numOfDoors++;
            }
            if (port.getY() > 6) {
                doorCordinateX[numOfDoors] = String.valueOf(port.getX() + 45);
                doorCordinateY[numOfDoors] = String.valueOf(port.getY() - 6);
                numOfDoors++;
            }
            if (port.getX() < 1104) {
                doorCordinateX[numOfDoors] = String.valueOf(port.getX() + 96);
                doorCordinateY[numOfDoors] = String.valueOf(port.getY() + 30);
                numOfDoors++;
            }
            if (port.getY() < 534) {
                doorCordinateX[numOfDoors] = String.valueOf(port.getX() + 45);
                doorCordinateY[numOfDoors] = String.valueOf(port.getY() + 66);
                numOfDoors++;
            }
        }

//            for(int i = 0 ; i < 12 ; i++){
//                String x1 , y1 ;
//                x1 = doorCordinateX[i];
//                y1 = doorCordinateY[i];
//                System.out.println(x1 +","+ y1);
//                outputStreamWriter.write(doorCordinateX[i] + "," + doorCordinateY[i] +" ");
//
//        }

        for (int i = 0; i < 100; i++) {   // 100 ở đây là số đường đi
            Random rand1 = new Random();
            int random1 = rand1.nextInt(numOfDoors);
            Random rand2 = new Random();
            int random2 = rand2.nextInt(numOfDoors);
            outputStreamWriter.write(doorCordinateX[random1] + "," + doorCordinateY[random1] + " ");
            outputStreamWriter.write(doorCordinateX[random2] + "," + doorCordinateY[random2]);
            outputStreamWriter.write("\n");
        }

        outputStreamWriter.write(numberOfFacilities);  // in ra số đa giác
        outputStreamWriter.write("\n");

        for (Facility facility : facilities) {
            String xLeft = String.valueOf(facility.getX());
            String xRight = String.valueOf(facility.getX() + facility.getSize_x());
            String yTop = String.valueOf(facility.getY());
            String yBot = String.valueOf(facility.getY() + facility.getSize_y());
            String botLeft = xLeft + "," + yBot;
            String botRight = xRight + "," + yBot;
            String topLeft = xLeft + "," + yTop;
            String topRight = xRight + "," + yTop;

            String[] data = {
                    "4", " ",
                    topLeft, " ",
                    topRight, " ",
                    botRight, " ",
                    botLeft
            };

            for (String item : data) {
                try {
                    outputStreamWriter.write(item); //in dữ liệu

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outputStreamWriter.write("\n"); //xuong dong
        }

        //  ghi dữ liệu xong thì mới kết thúc chương trình.
        try {
            outputStreamWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void playAgainGame() {
        facilities.removeAllElements();
        agvArray = new ArrayList<>();
        constructData();
        for (Node node: lineArray) {
            node.agvPriority = 0;
        }
    }
}