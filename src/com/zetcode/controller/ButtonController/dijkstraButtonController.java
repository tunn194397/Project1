package com.zetcode.controller.ButtonController;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class dijkstraButtonController extends buttonController {
    public JButton playButton;
    public dijkstraButtonController(Board board, JButton button, JButton playButton) {
        super(board, button);
        this.playButton = playButton;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        playButton.setText("Play");
        board.setStatus(2);
    }
}
