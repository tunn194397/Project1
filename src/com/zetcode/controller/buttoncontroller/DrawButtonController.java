package com.zetcode.controller.buttoncontroller;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DrawButtonController extends ButtonController {
    public JButton playButton;
    public DrawButtonController(Board board, JButton button) {
        super(board, button);
    }
    public DrawButtonController(Board board, JButton button, JButton playButton) {
        super(board, button);
        this.playButton = playButton;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.getText().equals("Draw Line")) {
            playButton.setText("Play");
            board.pauseGame();
            board.setStatus(1);
            button.setText("Resume");
        }
        else {
            button.setText("Draw Line");
            board.setStatus(0);
        }
    }
}
