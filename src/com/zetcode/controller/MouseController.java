package com.zetcode.controller;

import com.zetcode.model.*;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Vector;

public class MouseController extends MouseAdapter implements MouseWheelListener {
    // Nhung thuoc tinh lien quan den Board
    public Board board;
    public Vector<Facility> facilities;
    public int status;
    public Facility collector;
    public Node[][] nodeArray;
    public ArrayList<Node> lineArray = new ArrayList<>();

    //Nhung thuoc tinh dung de xu li su kien
    public Node firstNode = new Node(), lastNode = new Node();
    Vector <Node> tmp = new Vector<>();
    public MouseController(){
        super();
    }
    public MouseController(Board board) {
        super();
        this.board = board;
        updateController();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        board.turnOnMusic1(4);
        int click_x = e.getX();
        int click_y = e.getY();
        if (board.status == 0) {
            for (Facility facility : facilities) {
                if (facility.shape.isHit(click_x, click_y)) {
                    if (collector.ID.equals(facility.ID)) {
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
            }
            for (AGV agv : board.agvArray) {
                if (agv.shape.isHit(click_x, click_y)) {
                    if (collector.ID.equals(agv.ID)) {
                        collector = new Facility();
                        collector.setMovable(false);
                    } else {
                        collector = agv;
                        collector.setMovable(true);
                    }
                    updateBoard();
                    board.repaint();
                    break;
                }
            }
        }
        else if (board.status == 1) {
            if (firstNode.isNull) {
                firstNode = nodeArray[click_x / 30][click_y / 30];
                firstNode.updateDirection("head");
            } else if (lastNode.isNull){
                lastNode = nodeArray[click_x / 30][click_y / 30];
                lastNode.updateDirection("head");
                doDrawLineByTwoNode();
            } else {
                firstNode = nodeArray[click_x / 30][click_y / 30];
                firstNode.updateDirection("head");
                lastNode = new Node();
            }
            updateBoard();
            board.repaint();
        }
        else if (board.status == 4) {
            if (nodeArray[click_x/30][click_y/30].isLine) nodeArray[click_x/30][click_y/30].updateIsEndNode();
            else JOptionPane.showMessageDialog(board,"This node isn't in line!");
            board.setStatus(0);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int press_x = e.getX();
        int press_y = e.getY();
        if (status == 0) {
        }
        else if (status == 1) {
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.status == 0) {
            int drag_x = e.getX();
            int drag_y = e.getY();
            if (collector.moveAbility){
                doMove(e, drag_x, drag_y);
            }
        }
        else if (board.status == 1) {
        }
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (!collector.ID.equals("Null")) {
            doScale(e);
        }
    }


    // Nhung ham ho tro draw line
    public void doDrawLineByTwoNode(){
        if ( firstNode.y == lastNode.y ) {  //Ve duong di theo phuong ngang
            int bothY = firstNode.y/30;
            if (firstNode. x > lastNode.x) {
                for (int i = firstNode.x/30; i >= lastNode.x/30; i -- ) {
                    tmp.add(nodeArray[i][bothY]);
                }
                if (checkDrawLine()) updateTmp("left");
                else resetDraw();
            }
            else {
                for (int i = firstNode.x/30; i <= lastNode.x/30; i ++ ) {
                    tmp.add(nodeArray[i][bothY]);
                }
                if (checkDrawLine()) updateTmp("right");
                else resetDraw();
            }
        }
        else if ( firstNode.x == lastNode.x) {
            int bothX = firstNode.x/30;
            if (firstNode.y >= lastNode.y) {
                for (int i = firstNode.y/30; i >= lastNode.y/30; i -- ) {
                    tmp.add(nodeArray[bothX][i]);
                }
                if (checkDrawLine()) updateTmp("up");
                else resetDraw();
            }
            else {
                for (int i = firstNode.y/30; i <= lastNode.y/30; i ++ ) {
                    tmp.add(nodeArray[bothX][i]);
                }
                if (checkDrawLine()) updateTmp("down");
                else resetDraw();
            }
        }
        else {
            firstNode.updateIsLine(false);
            lastNode.updateIsLine(false);
            firstNode = new Node();
            lastNode = new Node();
        }
        for (Node node : tmp) {
            if (!lineArray.contains(node)) lineArray.add(node);
        }
        tmp.removeAllElements();
        updateBoard();
        board.repaint();
    }
    public boolean checkDrawLine() {
        boolean ok = true;
        for (Facility facility : facilities) {
            for (Node node : tmp) {
                if (node.isBelongTo(facility)) {
                    ok = false;
                    break;
                }
            }
            if (!ok) break;
        }
        return ok;
    }
    public void updateTmp(String s) {
        for (int i = 0; i < tmp.size() - 1; i ++) {
            tmp.get(i).updateDirection(s);
            if (s.equals("left")) tmp.get(i).setLeft(tmp.get(i+1));
            if (s.equals("right")) tmp.get(i).setRight(tmp.get(i+1));
            if (s.equals("up")) tmp.get(i).setUp(tmp.get(i+1));
            if (s.equals("down")) tmp.get(i).setDown(tmp.get(i+1));
        }
        tmp.get(tmp.size() -1).updateDirection(s);
    }
    public void resetDraw(){
        board.turnOnMusic1(2);
        JOptionPane.showMessageDialog(board,"Cannot make line because line cannot go through the facility");
        firstNode.updateIsLine(false);
        lastNode.updateIsLine(false);
        firstNode = new Node();
        lastNode = new Node();
    }

    //Nhung ham ho tro cho di chuyen cac Facility trong Board
    public void doMove(MouseEvent e, int x, int y) {
        if (collector.shape.isHit(x, y)) {
            collector.update(e.getX() - collector.size_x/2,e.getY() - collector.size_y/2);
            updateBoard();
        }
    }

    // Ham ho tro size update
    private void doScale(MouseWheelEvent e) {

        int wheel_x = e.getX();
        int wheel_y = e.getY();

        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            if (!collector.ID.equals("Null")){
                if (collector.shape != null) {
                    if (collector.shape.isHit(wheel_x, wheel_y)) {
                        float amount =  e.getWheelRotation() * 5f;
                        collector.updateSize((int)amount);
                        updateBoard();
                        board.repaint();
                    }
                }
            }
        }
    }

    // Ham de update Controlle va Board sau moi lan active
    public void updateController(){
        facilities = board.facilities;
        status = board.status;
        collector = board.collector;
        nodeArray = board.nodeArray;
        lineArray = board.lineArray;
    }
    public void updateBoard(){
        board.facilities = facilities;
        board.status = status;
        board.collector = collector;
        board.nodeArray = nodeArray;
        board.lineArray = lineArray;
    }

}
