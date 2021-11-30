package com.zetcode.controller.buttoncontroller;

import com.zetcode.model.Facility;
import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteButtonController extends ButtonController{

    public DeleteButtonController(Board board, JButton button) {
        super(board, button);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (board.collector.ID != "Null"){
            board.facilities.removeIf(facility -> facility.equals(board.collector));
            board.collector = new Facility();
            board.collector.setMovable(false);
        }
        else {
            JOptionPane.showMessageDialog(board,"Collect one facility to delete!");
        }
    }
}
