package com.zetcode.controller.buttoncontroller;

import javax.swing.JButton;

import com.zetcode.view.Board;
import java.awt.event.ActionEvent;
public class SaveButtonController extends ButtonController{
        public JButton playButton;
        public SaveButtonController(Board board, JButton button) {
            super(board, button);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Save Button");
            board.saveGame();
        }
    }

