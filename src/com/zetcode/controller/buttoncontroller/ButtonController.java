package com.zetcode.controller.buttoncontroller;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    public Board board;
    public JButton button;
    public ButtonController(Board board, JButton button) {
        super();
        this.board = board;
        this.button = button;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
