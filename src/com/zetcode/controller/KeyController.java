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
            board.rightDirection = false;board.leftDirection = false;
            System.out.println(" v ");
            board.mainAGV.switchSide();
        }
    }
}
