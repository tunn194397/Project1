package com.zetcode.view;

//import com.zetcode.algorithm.ReadInput;
import com.zetcode.controller.MouseController;
import com.zetcode.model.*;

import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import java.util.Vector;

public class Board extends JPanel implements ActionListener {
    // Các biến toàn cục trong Board
    
    public final int B_WIDTH = 1200;
    public final int B_HEIGHT = 600;
    private final int DOT_SIZE = 10;
    private final int RAND_POS = 29;
    private final int DELAY = 333;

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
    public Node[][] nodeArray = new Node[B_WIDTH/30][B_HEIGHT/30];
    public AGV mainAGV;
    public Vector<Agent> agArray = new Vector<>();
    public Vector<Node> lineArray = new Vector<>();
    public Vector<Room> roomArray = new Vector<>();
    public Vector<AGV> agvArray = new Vector<>();
    public Vector<Gurney> gurneyArray = new Vector<>();
    public Vector<Lift> liftArray = new Vector<>();
    public Vector<Port> portArray = new Vector<>();
    public Vector<Person> personArray = new Vector<>();

    public Facility collector = new Facility();
    public Map map = new Map();
    public Sound sound = new Sound();

    public Vector<Facility> facilities = new Vector<>();

    //public ReadInput readInput = new ReadInput();
    public String[] doorCordinateX;
    public String[] doorCordinateY;
    public int numOfDoors;


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
        turnOnMusic(1);
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
        mainAGV.draw(g);
        // Ve 4 cong vao ra
        for (Facility facility: facilities) {
            facility.draw(g);
        }
        for (AGV agv: agvArray) {
            agv.draw(g);
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
        //readInput.drawRoute(g);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        moveAGV();
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
        for(Room r: roomArray){
            for(int i = 0; i < r.agentNum; i++){
                agArray.add(r.agentArray[i]);
                System.out.println("size of agArray "+agArray.size());
                AgentJS agJS = new AgentJS(r.agentArray[i]);
                newMap.add(agJS);
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
      for (Node[] nodes: nodeArray) {
        for (Node node: nodes){
            if (node.isLine) System.out.println(node.ID);
        }
    }
      //agArray = map.LoadAgent(nameFile);
    }

    public void moveAGV() {
        mainAGV.move();
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
        liftArray.add(new Lift(1, 240));
        liftArray.add(new Lift(1, 300));
        liftArray.add(new Lift(1110, 240));
        liftArray.add(new Lift(1110, 300));

        updateFacilities();

        mainAGV = new AGV(120,120,nodeArray);
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
        updateAGV();
        System.out.println("Updated line graph");
    }
    public void updateAGV() {
        mainAGV.nodeArray = nodeArray;
        System.out.println("Update AGV: " + mainAGV.x + " " + mainAGV.y);
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

    public void turnOnMusic(int i){
        sound.setFile(i);
        sound.playSound();
        sound.loopSound();
    }
    public void turnOffMusic(){
        sound.stopSound();
    }
    public void turnOnMusic1(int i){
        sound.setFile(i);
        sound.playSound();
    }

}


