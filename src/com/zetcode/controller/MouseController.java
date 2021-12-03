package com.zetcode.controller;

import com.zetcode.model.Facility;
import com.zetcode.model.Node;
import com.zetcode.view.Board;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseController extends MouseAdapter implements MouseWheelListener {
    public Board board;
    public Facility[] facilities;
    public int status;
    public Facility collector;
    public Node[][] nodeArray;
    public int x;
    public int y;
    public MouseController(){
        super();
    }
    public MouseController(Board board){
        super();
        this.board = board;
        updateController();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int click_x = e.getX();
        int click_y = e.getY();
        if (status == 0) {
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
        else if (status == 1) {

        }

    }
    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        System.out.println("press Mouse in " + x + " " + y);
        if (status == 2) {
            board.dijkstra.getMouseData(e);
            board.setStatus(3);
        } else if (status == 3) {
            board.dijkstra.getMouseData(e);
            board.setStatus(4);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (status == 0) {
            System.out.println(e.getX()+ " " + e.getY());
            if (collector.moveAbility){
                doMove(e);
            }
        }
        else if (status == 1) {
            doDrawLine(e);
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
    }
    public void updateBoard(){
        board.facilities = facilities;
        board.status = status;
        board.collector = collector;
        board.nodeArray = nodeArray;
    }

}
