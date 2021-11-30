package com.zetcode.controller.buttoncontroller;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ResizeButtonController extends ButtonController {
    public JButton playButton;
    public ResizeButtonController(Board board, JButton button) {
        super(board, button);
    }
    public ResizeButtonController(Board board, JButton button, JButton playButton) {
        super(board, button);
        this.playButton = playButton;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (board.collector.ID != "Null"){
            board.pauseGame();
            board.status = 3;
            playButton.setText("Play");
        }
        else {
            JOptionPane.showMessageDialog(board,"Collect one facility to resize!");
        }
    }
}
