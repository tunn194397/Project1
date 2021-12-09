package com.zetcode.controller.buttoncontroller;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.zetcode.view.Board;
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

