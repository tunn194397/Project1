package com.zetcode.controller;

import com.zetcode.model.AGV;
import com.zetcode.model.Direction;
import com.zetcode.view.Board;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {

    public Board board;
    public KeyController(Board board) {
        super();
        this.board = board;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        AGV mainAGV = board.mainAGV;

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            System.out.printf(""+ board.nodeArray[mainAGV.x/30][mainAGV.y/30].direction.right);
            if (board.nodeArray[mainAGV.x/30][mainAGV.y/30].direction.left == 1 ) {
                mainAGV.direction = new Direction("left");
            }
            else mainAGV.direction = new Direction();
            System.out.printf(""+ board.nodeArray[mainAGV.x/30][mainAGV.y/30].direction.right);
        }
        if (key == KeyEvent.VK_RIGHT){
            if (board.nodeArray[mainAGV.x/30][mainAGV.y/30].direction.right == 1 ) {
                mainAGV.direction = new Direction("right");
            }
            else mainAGV.direction = new Direction();
        }
        if (key == KeyEvent.VK_UP) {
            if (board.nodeArray[mainAGV.x/30][mainAGV.y/30].direction.up == 1 ) {
                mainAGV.direction = new Direction("up");
            }
            else mainAGV.direction = new Direction();
        }
        if (key == KeyEvent.VK_DOWN) {
            if (board.nodeArray[mainAGV.x/30][mainAGV.y/30].direction.down == 1 ) {
                mainAGV.direction = new Direction("down");
            }
            else mainAGV.direction = new Direction();
        }
        if (key == KeyEvent.VK_NUMPAD0) {
            if (mainAGV.direction.numberOfDirection() == 0) {
                mainAGV.direction = board.nodeArray[mainAGV.x/30][mainAGV.y/30].direction;
            }
            else mainAGV.direction = new Direction();
        }
    }
}
