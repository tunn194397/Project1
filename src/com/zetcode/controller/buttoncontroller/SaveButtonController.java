package com.zetcode.controller.buttoncontroller;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
public class SaveButtonController extends ButtonController{
        public static String nameMap;

        public SaveButtonController(Board board, JButton button) {
            super(board, button);
        }
        @Override
        public void actionPerformed(ActionEvent e) {   
            nameMap = null;
            nameMap = JOptionPane.showInputDialog("Name Of Map");
            if(nameMap != null)board.saveGame();
           
        }
    }

