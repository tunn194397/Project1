package com.zetcode.controller.buttoncontroller;

import com.zetcode.model.Room;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PlayButtonController extends ButtonController {

    public JButton drawButton;
    public PlayButtonController(Board board, JButton button) {
        super(board, button);
    }
    public PlayButtonController(Board board, JButton button, JButton drawButton) {
        super(board, button);
        this.drawButton = drawButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.getText().equals("Play")) {
            drawButton.setText("Draw Line");
            board.initGame();
            button.setText("Pause");
            board.setStatus(0);
        }
        else {
            board.pauseGame();
            button.setText("Play");
            board.setStatus(0);
        }
    }
}
