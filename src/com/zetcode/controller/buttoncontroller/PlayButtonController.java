package com.zetcode.controller.buttoncontroller;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class PlayButtonController extends ButtonController {

    public JButton drawButton;
    public static boolean gameStart = false;

    public static boolean isGameStart() {
        return gameStart;
    }

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
            board.turnOffMusic();
            board.turnOnMusic(6);
            drawButton.setText("Draw Line");
            board.initGame();
            gameStart = true;
            button.setText("Pause");
            button.setToolTipText("Pause");
            ImageIcon pauseIcon = new ImageIcon("src/images/boardButton/pause.png");
            button.setIcon(pauseIcon);
            board.setStatus(0);
            board.updateLineGraph();
	        try {
                board.printResult();
                board.readInput.readInput();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            board.turnOffMusic();
            board.pauseGame();
            button.setText("Play");
            button.setToolTipText("Play");
            ImageIcon playIcon = new ImageIcon("src/images/boardButton/play.png");
            button.setIcon(playIcon);
            board.setStatus(0);
        }
    }
}
