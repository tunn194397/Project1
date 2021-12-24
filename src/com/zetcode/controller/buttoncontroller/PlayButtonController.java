package com.zetcode.controller.buttoncontroller;

import com.zetcode.model.Node;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

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
            for (Node node : board.lineArray) {
                System.out.print("["+ node.coordinate_x+ "; " + node.coordinate_y+ "]");
                if (node.Up != null) System.out.print("--> "+ "["+ node.Up.coordinate_x+ "; " + node.Up.coordinate_y+ "]");
                if (node.Down != null) System.out.print("--> "+ "["+ node.Down.coordinate_x+ "; " + node.Down.coordinate_y+ "]");
                if (node.Right != null) System.out.print("--> "+ "["+ node.Right.coordinate_x+ "; " + node.Right.coordinate_y+ "]");
                if (node.Left != null) System.out.print("--> "+ "["+ node.Left.coordinate_x+ "; " + node.Left.coordinate_y+ "]");
                System.out.println("");
            }
	        try {

                board.printResult();               //board.readInput.readInput();
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
