package com.zetcode;

import com.zetcode.model.Facility;
import com.zetcode.model.Lift;
import com.zetcode.model.Port;
import com.zetcode.model.Room;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.*;



public class Main extends JFrame {
    Board mainBoard = new Board();
    public Main() {
        initUI();
    }
    private void initUI() {
        add(mainBoard);
        constructData();
        setResizable(false);
        pack();
        setTitle("Hospital");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Main();
            ex.setVisible(true);
        });
    }

    private void constructData() {
        Port p1 = new Port(1,1);
        Port p2 = new Port(1110,1);
        Port p3 = new Port(1,540);
        Port p4 = new Port(1110,540);
        mainBoard.portArray = new Port[]{p1,p2,p3,p4};

        Lift l1 = new Lift(1,240);
        Lift l2 = new Lift(1110,240);
        Lift l3 = new Lift(1,300);
        Lift l4 = new Lift(1110,300);
        mainBoard.liftArray = new Lift[]{l1,l2,l3,l4};

        Room r1 = new Room(150,60);
        Room r2 = new Room(480,60);
        Room r3 = new Room(150,360);
        Room r4 = new Room(480,360);
        Room r5 = new Room(810,60);
        Room r6 = new Room(810,360);
        mainBoard.roomArray = new Room[]{r1, r2, r3, r4, r5, r6};

        mainBoard.facilities = new Facility[]{p1,p2,p3,p4,r1,r2,r3,r4,r5,r6,l1,l2,l3,l4,
                r1.doorArray[0],r1.doorArray[1], r1.doorArray[2],r1.doorArray[3],
                r2.doorArray[0],r2.doorArray[1], r2.doorArray[2],r2.doorArray[3],
                r2.doorArray[0],r2.doorArray[1], r2.doorArray[2],r2.doorArray[3],
                r2.doorArray[0],r2.doorArray[1], r2.doorArray[2],r2.doorArray[3], };

    }
}
