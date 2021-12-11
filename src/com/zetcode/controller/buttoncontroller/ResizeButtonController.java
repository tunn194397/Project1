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
            if (playButton.getText().equals("Play")) {
                board.setStatus(2);
            }
            else {
                playButton.setText("Play");
                board.setStatus(0);
            }
        }
        else {
            JOptionPane.showMessageDialog(board,"Collect one facility to resize!");
        }
    }
}
