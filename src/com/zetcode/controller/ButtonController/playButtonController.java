package com.zetcode.controller.ButtonController;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class playButtonController extends buttonController {

    public JButton drawButton;
    public playButtonController(Board board, JButton button) {
        super(board, button);
    }
    public playButtonController(Board board, JButton button, JButton drawButton) {
        super(board, button);
        this.drawButton = drawButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.getText().equals("Play")) {
            drawButton.setText("Draw Line");
            board.initGame();
            button.setText("Pause");
        }
        else {
            board.pauseGame();
            button.setText("Play");
        }
    }
}
