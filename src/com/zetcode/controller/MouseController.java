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
    // Nhung thuoc tinh lien quan den Board
    public Board board;
    public Vector<Facility> facilities;
    public int status;
    public Facility collector;
    public Node[][] nodeArray;
    public Vector<Node> lineArray = new Vector<>();

    //Nhung thuoc tinh dung de xu li su kien
    public Node firstNode = new Node(), lastNode = new Node();
    Vector <Node> tmp = new Vector<>();
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
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int press_x = e.getX();
        int press_y = e.getY();
        if (status == 0) {
            System.out.println("press Mouse in " + press_x + " " + press_y);
        }
        else if (status == 1) {

        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (board.status == 0) {
            int drag_x = e.getX();
            int drag_y = e.getY();
            System.out.println(drag_x+ " " + drag_y);
            if (collector.moveAbility){
                doMove(e, drag_x, drag_y);
            }
        }
        else if (board.status == 1) {
//            doDrawLine(e);
            System.out.println(e.getX()+ " " + e.getY());
        }

    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (collector.ID.equals("Null")) {
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
        lineArray.addAll(tmp);
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
        JOptionPane.showMessageDialog(board,"Cannot make line because line cannot go through the facility");
        firstNode.updateIsLine(false);
        lastNode.updateIsLine(false);
        firstNode = new Node();
        lastNode = new Node();
    }

    //Nhung ham ho tro cho di chuyen cac Facility trong Board
    public void doMove(MouseEvent e, int x, int y) {
        int dx = e.getX() - x;
        int dy = e.getY() - y;

        if (collector.shape.isHit(x, y)) {
            collector.update(e.getX() - 60,e.getY() - 45);
            System.out.println(collector.x+ " " + collector.y);
            updateBoard();
//            board.repaint();
        }
        x += dx;
        y += dy;
    }
    public void doDrawLine(MouseEvent e, int x, int y)  {
        int dx = e.getX() - x;
        int dy = e.getY() - y;
        nodeArray[e.getX()/30][e.getY()/30].updateDirection("true");
        updateBoard();
        board.repaint();
        x += dx;
        y += dy;
    }

    // Ham ho tro size update
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
