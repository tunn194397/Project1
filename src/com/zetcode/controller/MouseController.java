package com.zetcode.controller;

import com.zetcode.model.Facility;
import com.zetcode.model.Node;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

public class MouseController extends MouseAdapter implements MouseWheelListener {
    public Board board;
    public Vector<Facility> facilities;
    public int status;
    public Facility collector;
    public Node[][] nodeArray;
    public Node firstNode, lastNode;
    public int x;
    public int y;
    public MouseController(){
        super();
    }
    public MouseController(Board board){
        super();
        this.board = board;
        updateController();
        for (Facility facility : facilities) {
            System.out.println(facility.ID);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int click_x = e.getX();
        int click_y = e.getY();
        System.out.println("Click in " + click_x+ " " + click_y);
        if (board.status == 0) {
            for (Facility facility : facilities) {
                if (facility.shape.isHit(click_x, click_y)) {
                    System.out.println("Click Mouse in" + facility.ID);
                    if (collector == facility) {
                        collector = new Facility();
                        collector.setMovable(false);
                    } else {
                        collector = facility;
                        collector.setMovable(true);
                    }
                    updateBoard();
                    board.repaint();
                    break;
                }
                else {};
            }
        }
        else if (board.status == 1) {
            System.out.println("DrawLine");
            if (firstNode.isNull) {
                firstNode = nodeArray[click_x / 30][click_y / 30];
                firstNode.updateDirection(5);
            } else if (lastNode.isNull){
                lastNode = nodeArray[click_x / 30][click_y / 30];
                lastNode.updateDirection(6);
                doDrawLineByTwoNode();
            } else {
                firstNode = nodeArray[click_x / 30][click_y / 30];
                firstNode.updateDirection(5);
                lastNode = new Node();
            }
            updateBoard();
            board.repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if (status == 0) {
            System.out.println("press Mouse in " + x + " " + y);
        }
        else if (status == 1) {

        }
    }

    public void doDrawLineByTwoNode(){
        if ( Math.abs(firstNode.y - lastNode.y) < 60 ) {  //Ve duong di theo phuong ngang
            lastNode.updateNode(lastNode.x, firstNode.y);
            int bothY = firstNode.y/30;
            if (firstNode. x > lastNode.x) {
                boolean ok = true;
                for (int i = firstNode.y/30; i >= lastNode.y/30; i --) {
                    for (Facility facility : facilities) {
                        if (nodeArray[i][bothY].isBelongTo(facility)) {
                            ok = false;
                            break;
                        }

                    }
                    if (!ok) break;
                }
                if (ok) for (int i = firstNode.x /30; i >= lastNode.x/30; i --) {
                    board.firstNode.updateDirection(2);
                    board.lastNode.updateDirection(2);
                    nodeArray[i][bothY].updateDirection(2); //Update no la left direction
                }
                else resetDraw();
            }
            else {
                boolean ok = true;
                for (int i = firstNode.y/30; i <= lastNode.y/30; i ++) {
                    for (Facility facility : facilities) {
                        if (nodeArray[i][bothY].isBelongTo(facility)) {
                            ok = false;
                            break;
                        }
                    }
                    if (!ok) break;
                }
                if (ok) for (int i = firstNode.x /30; i <= lastNode.x/30; i ++) {
                    board.firstNode.updateDirection(1);
                    board.lastNode.updateDirection(1);
                    nodeArray[i][bothY].updateDirection(1); //Update no la right direction
                }
                else resetDraw();
            }
        }
        else if ( Math.abs(firstNode.x - lastNode.x) < 60) {
            lastNode.updateNode(firstNode.x, lastNode.y);
            int bothX = firstNode.x/30;
            if (firstNode.y >= lastNode.y) {
                boolean ok = true;
                for (int i = firstNode.y/30; i >= lastNode.y/30; i --) {
                    for (Facility facility : facilities) {
                        if (nodeArray[bothX][i].isBelongTo(facility)) {
                            ok = false;
                            break;
                        }
                    }
                    if (!ok) break;
                }
                if (ok) {
                    for (int i = firstNode.y/30; i >= lastNode.y/30; i --) {
                        board.firstNode.updateDirection(3);
                        board.lastNode.updateDirection(3);
                        nodeArray[bothX][i].updateDirection(3); //Update no la up direction
                    }
                }
                else resetDraw();
            }
            else {
                boolean ok = true;
                for (int i = firstNode.y/30; i <= lastNode.y/30; i ++) {
                    for (Facility facility : facilities) {
                        if (nodeArray[bothX][i].isBelongTo(facility)) {
                            ok = false;
                            break;
                        }
                    }
                    if (!ok) break;
                }
                if (ok) for (int i = firstNode.y/30; i <= lastNode.y/30; i ++) {
                    board.firstNode.updateDirection(4);
                    board.lastNode.updateDirection(4);
                    nodeArray[bothX][i].updateDirection(4); //Update no la down direction
                }
                else resetDraw();
            }
        }

        updateBoard();
        board.repaint();
    }

    public void resetDraw(){
        JOptionPane.showMessageDialog(board,"Cannot make line because line cannot go through the facility");
        firstNode = new Node();
        lastNode = new Node();
        board.firstNode.updateDirection(0);
        board.lastNode.updateDirection(0);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.status == 0) {
            System.out.println(e.getX()+ " " + e.getY());
            if (collector.moveAbility){
                doMove(e);
            }
        }
        else if (board.status == 1) {
//            doDrawLine(e);
            System.out.println(e.getX()+ " " + e.getY());
        }

    }

    public void doMove(MouseEvent e) {
        int dx = e.getX() - x;
        int dy = e.getY() - y;

        if (!collector.shape.isHit(x, y)) {
        } else {
            collector.update(e.getX() - 60,e.getY() - 45);
            System.out.println(collector.x+ " " + collector.y);
            updateBoard();
            board.repaint();
        }
        x += dx;
        y += dy;
    }
    public void doDrawLine(MouseEvent e)  {
        int dx = e.getX() - x;
        int dy = e.getY() - y;
        nodeArray[e.getX()/30][e.getY()/30].updateDirection(1);
        updateBoard();
        board.repaint();
        x += dx;
        y += dy;
    }
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
                    updateBoard();
                    board.repaint();
                }
            }
        }
    }
    public void updateController(){
        facilities = board.facilities;
        status = board.status;
        collector = board.collector;
        nodeArray = board.nodeArray;
        firstNode = board.firstNode;
        lastNode = board.lastNode;
    }
    public void updateBoard(){
        board.facilities = facilities;
        board.status = status;
        board.collector = collector;
        board.nodeArray = nodeArray;
        board.firstNode = firstNode;
        board.lastNode = lastNode;
    }


}