package com.zetcode.controller;

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

        int key = e.getKeyCode();
        if (board.status == 3) {
            if (key == KeyEvent.VK_LEFT) {board.collector.updateSize(board.collector.x - 5,board.collector.y);}
            if (key == KeyEvent.VK_RIGHT)  {board.collector.updateSize(board.collector.x + 5,board.collector.y);}
            if (key == KeyEvent.VK_UP) {board.collector.updateSize(board.collector.x ,board.collector.y-5);}
            if (key == KeyEvent.VK_DOWN)  {board.collector.updateSize(board.collector.x,board.collector.y+5);}
            board.repaint();
        }
        else {
            if ((key == KeyEvent.VK_LEFT) && (!board.rightDirection) && (!board.leftDirection)) {
                board.leftDirection = true;
                board.upDirection = false;
                board.downDirection = false;
                System.out.println("<---");
                board.mainAGV.switchSide();
            }

            if ((key == KeyEvent.VK_RIGHT) && (!board.leftDirection) && (!board.rightDirection)) {
                board.rightDirection = true;
                board.upDirection = false;
                board.downDirection = false;
                System.out.println("--->");
                board.mainAGV.switchSide();
            }

            if ((key == KeyEvent.VK_UP) && (!board.upDirection) && (!board.downDirection)) {
                board.upDirection = true;
                board.rightDirection = false;
                board.leftDirection = false;
                System.out.println(" ^ ");
                board.mainAGV.switchSide();
            }

            if ((key == KeyEvent.VK_DOWN) && (!board.upDirection) && (!board.downDirection)) {
                board.downDirection = true;
                board.rightDirection = false;
                board.leftDirection = false;
                System.out.println(" v ");
                board.mainAGV.switchSide();
            }
        }
    }
}
