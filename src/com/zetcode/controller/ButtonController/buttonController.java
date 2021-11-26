package com.zetcode.controller.ButtonController;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class buttonController implements ActionListener {
    public Board board;
    public JButton button;
    public buttonController(Board board, JButton button) {
        super();
        this.board = board;
        this.button = button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
