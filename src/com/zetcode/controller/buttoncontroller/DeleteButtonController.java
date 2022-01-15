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
        if (!board.collector.ID.equals("Null")){
            board.facilities.removeIf(f -> f.ID.equals(board.collector.ID));

            board.turnOnMusic1(7);
            for (Facility f : board.facilities) {
                System.out.println(f.ID);
            }

            if (board.collector.name.equals("Room")) {
                board.roomArray.removeIf(f->f.ID.equals(board.collector.ID));
            }
            if (board.collector.name.equals("Port")) {
                board.portArray.removeIf(f->f.ID.equals(board.collector.ID));
            }
            if (board.collector.name.equals("Lift")) {
                board.liftArray.removeIf(f->f.ID.equals(board.collector.ID));
            }
            if (board.collector.name.equals("AGV")) {
                board.agvArray.removeIf(f->f.ID.equals(board.collector.ID));
            }
            board.collector = new Facility();
            board.collector.setMovable(false);
        }
        else {
            System.out.println("No Delete");
            board.turnOnMusic1(2);
            JOptionPane.showMessageDialog(board,"Collect one facility to delete!");
        }
    }
}
