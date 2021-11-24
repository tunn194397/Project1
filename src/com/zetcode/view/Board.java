package com.zetcode.view;

import com.zetcode.model.Person;
import com.zetcode.controller.MovingAdapterController;
import com.zetcode.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;

public class Board extends JPanel implements ActionListener {
    private final int B_WIDTH = 1200;
    private final int B_HEIGHT = 600;
    private final int DOT_SIZE = 10;
    private final int MAX_PERSON = 10;
    private final int MAX_AGV = 10;
    private final int RAND_POS = 29;
    private final int DELAY = 140;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    private static AGV mainAGV = new AGV(120,120);
    public Room[] roomArray = new Room[8];
    public AGV[] agvArray = new AGV[6];
    public Gurney[] gurneyArray = new Gurney[6];
    public Lift[] liftArray = new Lift[4];
    public Port[] portArray = new Port[4];
    public Person[] personArray = new Person[10];
    public Facility collector = new Facility();
    public Facility[] facilities = new Facility[30];

    private Timer timer;


    public Board() {
        initBoard();
    }

    private void initBoard() {
        setBackground(Color.white);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();

        addKeyListener(new TAdapter());
        MovingAdapterController ma = new MovingAdapterController();
        addMouseListener(ma);
        addMouseMotionListener(ma);
        addMouseWheelListener(new ScaleHandler());
    }

    private void loadImages() {

    }
    private void initGame() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Ve cac duong ke
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
        // Ve 4 cong vao ra
        for (int i = 0; i < portArray.length; i ++) {
            portArray[i].draw(g);
        }
        for (int i = 0; i < liftArray.length; i ++) {
            liftArray[i].draw(g);
        }
        for (int i = 0; i < roomArray.length; i ++) {
            roomArray[i].draw(g);
        }
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
        doDrawing(g);
    }
    public void doDrawing(Graphics g) {
        mainAGV.draw(g);
        Toolkit.getDefaultToolkit().sync();
        if (!collector.ID.equals("Null")) {
            g.setColor(Color.gray);
            g.fillRect(collector.x, collector.y,collector.size_x,collector.size_y);};
    }

    private void move() {
        for (int i = 0; i < facilities.length; i ++) {
            if (mainAGV.checkCollision(facilities[i])){
                if (leftDirection) {
                    mainAGV.x += DOT_SIZE;
                    leftDirection = false;
                    upDirection = (mainAGV.y > 300)?true: false;
                    downDirection = (mainAGV.y <= 300)?true:false;
                    mainAGV.switchSide();
                }
                else if (rightDirection) {
                    mainAGV.x -= DOT_SIZE;
                    rightDirection = false;
                    upDirection = (mainAGV.y > 300)?true: false;
                    downDirection = (mainAGV.y <= 300)?true:false;
                    mainAGV.switchSide();
                }
                else if (upDirection) {
                    mainAGV.y += DOT_SIZE;
                    upDirection = false;
                    leftDirection =(mainAGV.x > 600)?true: false;
                    rightDirection = (mainAGV.x <= 600)?true:false;
                    mainAGV.switchSide();
                }
                else if (downDirection) {
                    mainAGV.y -= DOT_SIZE;
                    downDirection = false;
                    leftDirection =(mainAGV.x > 600)?true: false;
                    rightDirection = (mainAGV.x <= 600)?true:false;
                    mainAGV.switchSide();
                }
                break;
            }
        }
        if (mainAGV.y < 0) {
            mainAGV.y += DOT_SIZE;
            upDirection = false;
            downDirection = false;
            leftDirection =(mainAGV.x > 600)?true: false;
            rightDirection = (mainAGV.x <= 600)?true:false;
            mainAGV.switchSide();
        }
        else if (mainAGV.y > 570){
            mainAGV.y -= DOT_SIZE;
            upDirection = false;
            downDirection = false;
            leftDirection =(mainAGV.x > 600)?true: false;
            rightDirection = (mainAGV.x <= 600)?true:false;
            mainAGV.switchSide();
        }
        else if (mainAGV.x < 10){
            mainAGV.x += DOT_SIZE;
            leftDirection = false;
            rightDirection = false;
            upDirection = (mainAGV.y > 300)?true: false;
            downDirection = (mainAGV.y <= 300)?true:false;
            mainAGV.switchSide();
        }
        else if (mainAGV.x > 1170) {
            mainAGV.x -= DOT_SIZE;
            leftDirection = false;
            rightDirection = false;
            upDirection = (mainAGV.y > 300)?true: false;
            downDirection = (mainAGV.y <= 300)?true:false;
            mainAGV.switchSide();
        }
        else {
            if (leftDirection) {mainAGV.x -= DOT_SIZE;}
            if (rightDirection) {mainAGV.x += DOT_SIZE;}
            if (upDirection) {mainAGV.y -= DOT_SIZE;}
            if (downDirection) {mainAGV.y += DOT_SIZE;}
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
//            if (mainAGV.checkCollision(portArray[0]) ) {
//                timer.stop();
//                if (key == KeyEvent.VK_LEFT) mainAGV.x -= DOT_SIZE;
//                if (key == KeyEvent.VK_RIGHT) mainAGV.x += DOT_SIZE;
//                if (key == KeyEvent.VK_DOWN) mainAGV.y += DOT_SIZE;
//                if (key == KeyEvent.VK_UP) mainAGV.y -= DOT_SIZE;
//                repaint();
//            }

//
            if ((key == KeyEvent.VK_LEFT) && (!rightDirection) && (!leftDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
                mainAGV.switchSide();
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection) && (!rightDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
                mainAGV.switchSide();
            }

            if ((key == KeyEvent.VK_UP) && (!upDirection) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
                mainAGV.switchSide();
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection) && (!downDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
                mainAGV.switchSide();
            }
        }
    }
    class MovingAdapterController extends MouseAdapter {

        private int x;
        private int y;

        @Override
        public void mouseClicked(MouseEvent e) {
            int click_x = e.getX();
            int click_y = e.getY();
            for (int i =0; i < facilities.length; i ++ ) {
                if (facilities[i].shape.isHit(click_x, click_y)) {
                    System.out.println("Click Mouse in" + facilities[i].ID);
                    if (collector == facilities[i]) {
                        collector = new Facility();
                        collector.setMovable(false);
                    }
                    else {
                        collector = facilities[i] ;
                        collector.setMovable(true);
                    }
                    repaint();
                    break;
                }
            }

        }
        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
            System.out.println("press Mouse in " + x + " " + y);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (collector.moveAbility == true ){
                doMove(e);
            }
        }

        private void doMove(MouseEvent e) {
            int dx = e.getX() - x;
            int dy = e.getY() - y;

            if (collector.shape.isHit(x, y))  {
                collector.update(e.getX() - 60,e.getY() - 45);
                System.out.println(collector.x+ " " + collector.y);
                repaint();
            }
            x += dx;
            y += dy;
        }
    }
    class ScaleHandler implements MouseWheelListener{
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            doScale(e);
            System.out.println("Mouse Wheel in "+ e.getX()+ " " + e.getY());
        }

        private void doScale(MouseWheelEvent e) {

            int wheel_x = e.getX();
            int wheel_y = e.getY();

            if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
                if (!collector.ID.equals("Null")){
                    if (collector.shape.isHit(wheel_x, wheel_y)) {
                        float amount =  e.getWheelRotation() * 5f;
                        collector.updateSize((int)amount);
                        System.out.println(collector.size_x+ " "+ collector.size_y);
                        repaint();
                    }
                }
            }
        }
    }

}


