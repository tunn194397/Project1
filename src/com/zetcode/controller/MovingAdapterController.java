package com.zetcode.controller;

import com.zetcode.view.Board;
import com.zetcode.model.Facility;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovingAdapterController extends MouseAdapter {

    private int x;
    private int y;

//    public Board board;
//     private Facility[] facilities;
//     private Facility collector;
//     public Facility[] getFacilities() {
//         return facilities;
//     }
//
//     public void setFacilities(Facility[] facilities) {
//         this.facilities = facilities;
//     }
//
//     public Facility getCollector() {
//         return collector;
//     }
//
//     public void setCollector(Facility collector) {
//         this.collector = collector;
//     }
//    public void setBoard(Board board) {
//        this.board = board;
//    }
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        int click_x = e.getX();
//        int click_y = e.getY();
//        for (int i =0; i < facilities.length; i ++ ) {
//            if (facilities[i].shape.isHit(click_x, click_y)) {
//                System.out.println("Click Mouse in" + facilities[i].ID);
//                if (collector == facilities[i]) {
//                    collector = new Facility();
//                    collector.setMovable(false);
//                }
//                else {
//                    collector = facilities[i] ;
//                    collector.setMovable(true);
//                }
//                repaint();
//                break;
//            }
//        }
//
//    }
//    @Override
//    public void mousePressed(MouseEvent e) {
//        x = e.getX();
//        y = e.getY();
//        System.out.println("press Mouse in " + x + " " + y);
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        if (collector.moveAbility == true ){
//            doMove(e);
//        }
//    }
//
//    private void doMove(MouseEvent e) {
//        int dx = e.getX() - x;
//        int dy = e.getY() - y;
//
//        if (this.board.collector.shape.isHit(x, y))  {
//            this.board.collector.update(e.getX() - 60,e.getY()-45);
//            System.out.println(collector.x+ " " + collector.y);
//            repaint();
//        }
//
//        x += dx;
//        y += dy;
//    }
//
//    public void repaint(Board board) {
//        board.repaint();
//    }
}