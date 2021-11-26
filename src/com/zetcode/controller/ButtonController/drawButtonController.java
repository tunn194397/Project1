package com.zetcode.controller.ButtonController;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class drawButtonController extends buttonController{
    public JButton playButton;
    public drawButtonController(Board board, JButton button) {
        super(board, button);
    }
    public drawButtonController(Board board, JButton button, JButton playButton) {
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
