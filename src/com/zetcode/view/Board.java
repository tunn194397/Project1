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
    public int status = 0; // 0 la binh thuong, 1 la ve duong cho AGV, 2 la resize, 4 la chon node cuoi
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
    public Node endNode = new Node();

    public Facility collector = new Facility();
    public Map map = new Map();

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
        for (AGV agv : agvArray) {
            agv.draw(g);
        }
        // Ve 4 cong vao ra
        for (Facility facility: facilities) {
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
        for (AGV agv : agvArray) {
            agv.move();
        }
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
        int p = 0; 
        int pAGV = 0;
        Map newMap = new Map();
        for(Facility facility: facilities){
            FacilityJS facilityJS = new FacilityJS(facility);
            newMap.add(facilityJS);
            if("Port".equals(facility.name)) p = p+1;
            if("Lift".equals(facility.name)) pAGV = pAGV +1;
        }
        for (int i = 0; i < B_WIDTH/30; i ++) {
            for (int j = 0; j < B_HEIGHT/30 ; j ++ ) {
                NodeJS nodeJS = new NodeJS(nodeArray[i][j]);
                newMap.add(nodeJS);
            }
        }
        if(p < 2)JOptionPane.showMessageDialog(this, "So luong cong cho nguoi phai >= 2","Map khong hop le", JOptionPane.WARNING_MESSAGE);
        if(pAGV < 4)JOptionPane.showMessageDialog(this, "So luong cong cho AGV phai >= 4","Map khong hop le", JOptionPane.WARNING_MESSAGE);
        if((p>=2)&&(pAGV>=4)&&(p%2==1 || pAGV%2==1))JOptionPane.showMessageDialog(this, "So luong cong phai la so chan","Map khong hop le", JOptionPane.WARNING_MESSAGE);
        if((p>=2)&&(pAGV>=4)&&(p%2==0 && pAGV%2==0)) newMap.SaveMap();
    }

    public void loadGame(String nameFile){
      facilities = map.LoadFacilities(nameFile);
      map.LoadNode(nameFile);
      this.nodeArray = map.LoadNode(nameFile);
      
    }
    public void setUpNode() {
        for (int i = 0; i < B_WIDTH/30; i ++) {
            for (int j = 0; j < B_HEIGHT/30 ; j ++ ) {
                nodeArray[i][j] = new Node(i*30, j*30);
            }
        }
    }

    public void constructData() {
        for (int i = 0; i < B_WIDTH / 30; i++) {
            for (int j = 0; j < B_HEIGHT / 30; j++) {
                nodeArray[i][j] = new Node(i * 30, j * 30);
                System.out.println(nodeArray[i][j].coordinate_x + " " + nodeArray[i][j].coordinate_y);
            }
        }
        portArray.add(new Port(1, 1));
        portArray.add(new Port(1110, 1));
        portArray.add(new Port(1, 540));
        portArray.add(new Port(1110, 540));

        updateFacilities();

        mainAGV = new AGV(120,120,nodeArray);
        AGV agv1 = new AGV(210,60, nodeArray);
        AGV agv2 = new AGV(210,15, nodeArray);
        AGV agv3 = new AGV(420,450, nodeArray);
        AGV agv4 = new AGV(600,300, nodeArray);
        agvArray.add(mainAGV);
        agvArray.add(agv1);
        agvArray.add(agv2);
        agvArray.add(agv3);
        agvArray.add(agv4);
    }

    public boolean checkLine(){
        boolean oke = true;
        for (int i = 0; i < B_WIDTH/30; i ++) {
            for (int j = 0; j < B_HEIGHT/30; j ++ ) {
                // i là cot, j la hang
                int a, b, count = 0;
                if(i == 0){
                    if(nodeArray[i][j].direction.up == 1 && nodeArray[i][j-1].isLine && nodeArray[i][j-1].direction.down == 0){
                        if(nodeArray[i][j+1].direction.up == 1)count++;
                        if(nodeArray[i+1][j].direction.left == 1)count++;
                    }
                    if(nodeArray[i][j].direction.down== 1 && nodeArray[i][j+1].isLine && nodeArray[i][j+1].direction.up == 0){
                        if(nodeArray[i][j-1].direction.down == 1)count++;
                        if(nodeArray[i+1][j].direction.left == 1)count++;
                    }
                    if(nodeArray[i][j].direction.right == 1 && nodeArray[i+1][j].isLine && nodeArray[i+1][j].direction.left == 0){
                        if(nodeArray[i][j+1].direction.up == 1)count++;
                        if(nodeArray[i][j-1].direction.down== 1)count++;
                    }
                }
                if(i == B_WIDTH/30-1){
                    if(nodeArray[i][j].direction.up == 1 && nodeArray[i][j-1].isLine && nodeArray[i][j-1].direction.down == 0){
                        if(nodeArray[i][j+1].direction.up == 1)count++;
                        if(nodeArray[i-1][j].direction.right == 1)count++;
                    }
                    if(nodeArray[i][j].direction.down== 1 && nodeArray[i][j+1].isLine && nodeArray[i][j+1].direction.up == 0){
                        if(nodeArray[i][j-1].direction.down == 1)count++;
                        if(nodeArray[i-1][j].direction.right == 1)count++;
                    }
                    if(nodeArray[i][j].direction.left == 1 && nodeArray[i-1][j].isLine && nodeArray[i-1][j].direction.right == 0){
                        if(nodeArray[i][j+1].direction.up == 1)count++;
                        if(nodeArray[i][j-1].direction.down == 1)count++;
                    }
                }    
                if(j == 0){
                    if(nodeArray[i][j].direction.down== 1 && nodeArray[i][j+1].isLine && nodeArray[i][j+1].direction.up == 0){
                        if(nodeArray[i+1][j].direction.left == 1)count++;
                        if(nodeArray[i-1][j].direction.right == 1)count++;
                    }
                    if(nodeArray[i][j].direction.right == 1 && nodeArray[i+1][j].isLine && nodeArray[i+1][j].direction.left == 0){
                        if(nodeArray[i][j+1].direction.up == 1)count++;
                        if(nodeArray[i-1][j].direction.right == 1)count++;
                    }
                    if(nodeArray[i][j].direction.left == 1 && nodeArray[i-1][j].isLine && nodeArray[i-1][j].direction.right == 0){
                        if(nodeArray[i][j+1].direction.up == 1)count++;
                        if(nodeArray[i+1][j].direction.left == 1)count++;
                    }
                }
                if(j == B_HEIGHT/30-1){
                    if(nodeArray[i][j].direction.up == 1 && nodeArray[i][j-1].isLine && nodeArray[i][j-1].direction.down == 0){
                        if(nodeArray[i+1][j].direction.left == 1)count++;
                        if(nodeArray[i-1][j].direction.right == 1)count++;
                    }
                    if(nodeArray[i][j].direction.right == 1 && nodeArray[i+1][j].isLine && nodeArray[i+1][j].direction.left == 0){
                        if(nodeArray[i][j-1].direction.down== 1)count++;
                        if(nodeArray[i-1][j].direction.right == 1)count++;
                    }
                    if(nodeArray[i][j].direction.left == 1 && nodeArray[i-1][j].isLine && nodeArray[i-1][j].direction.right == 0){
                        if(nodeArray[i+1][j].direction.left == 1)count++;
                        if(nodeArray[i][j-1].direction.down == 1)count++;
                    }
                }
                if((i%39 != 0)&&(j%19 != 0)){
                    if(nodeArray[i][j].direction.up == 1 && nodeArray[i][j-1].isLine && nodeArray[i][j-1].direction.down == 0){
                        if(nodeArray[i][j+1].direction.up == 1)count++;
                        if(nodeArray[i+1][j].direction.left == 1)count++;
                        if(nodeArray[i-1][j].direction.right == 1)count++;
                    }
                    if(nodeArray[i][j].direction.down== 1 && nodeArray[i][j+1].isLine && nodeArray[i][j+1].direction.up == 0){
                        if(nodeArray[i][j-1].direction.down == 1)count++;
                        if(nodeArray[i+1][j].direction.left == 1)count++;
                        if(nodeArray[i-1][j].direction.right == 1)count++;
                    }
                    if(nodeArray[i][j].direction.right == 1 && nodeArray[i+1][j].isLine && nodeArray[i+1][j].direction.left == 0){
                        if(nodeArray[i][j+1].direction.up == 1)count++;
                        if(nodeArray[i][j-1].direction.down== 1)count++;
                        if(nodeArray[i-1][j].direction.right == 1)count++;
                    }
                    if(nodeArray[i][j].direction.left == 1 && nodeArray[i-1][j].isLine && nodeArray[i-1][j].direction.right == 0){
                        if(nodeArray[i][j+1].direction.up == 1)count++;
                        if(nodeArray[i+1][j].direction.left == 1)count++;
                        if(nodeArray[i][j-1].direction.down == 1)count++;
                    }
                }

                if(nodeArray[i][j].isLine && count == 0){ 
                    oke = false;
                    a = j+1;
                    b = i+1;
                    JOptionPane.showMessageDialog(this, "Node o hang "+a+" cot "+b+" khong hop le","Map khong hop le", JOptionPane.WARNING_MESSAGE);
                    break;
                }
            }
            if(oke == false) break;
        }
        return oke;
    }

    public void updateFacilities(){
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

        System.out.println("Nothing");
        for (Node node : lineArray) {
            if (node.isEndNode) {
                endNode = node;
                break;
            }
        };

        if (!endNode.ID.equals("Null")) {
            for (AGV agv: agvArray) {
                agv.findGraph(endNode);
                for (Node node : lineArray) {
                    node.setDist(1000);
                    node.setVisited(false);
                    node.setPr(new Node());
                }
            }
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