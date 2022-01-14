package com.zetcode.controller.buttoncontroller;

import com.zetcode.model.Agent;
import com.zetcode.model.Path;
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
            drawButton.setText("Draw Line");
            board.initGame();
            gameStart = true;
            button.setText("Pause");
            board.setStatus(0);
            board.updateLineGraph();
	        try {
                board.agentArray.clear();
                board.printResult();
                board.readInput.readInput();
                for (Path path : board.readInput.paths) {
                    Agent a = new Agent(path.route[0].getX(), path.route[0].getY());
                    board.agentArray.add(a);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            board.pauseGame();
            button.setText("Play");
            board.setStatus(0);
        }
    }
}
