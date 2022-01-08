package com.zetcode.controller.buttoncontroller;

import com.zetcode.model.Node;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChooseEndNodeController extends  ButtonController{
    public ChooseEndNodeController(Board board, JButton button) {
        super(board, button);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Node node : board.lineArray) {
            node.isEndNode = false;
        }
        board.pauseGame();
        board.setStatus(4);
    }
}
