package com.zetcode.controller.buttoncontroller;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PlayAgainController extends ButtonController {
    public JButton drawButton;
    public PlayAgainController(Board board, JButton button) {
        super(board, button);
    }
    public PlayAgainController(Board board, JButton button, JButton drawButton) {
        super(board, button);
        this.drawButton = drawButton;
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
            drawButton.setText("Draw Line");
            board.playAgainGame();
            board.setStatus(0);
//	        try {
//                board.printResult();
//                board.readInput.readInput();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }

    }
}
