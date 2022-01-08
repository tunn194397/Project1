package com.zetcode.controller.buttoncontroller;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.zetcode.view.Board;

public class LoadButtonController extends ButtonController{
    String nameFile;
    public LoadButtonController(Board board, JButton button) {
        super(board, button);
        
    }
    public void actionPerformed(ActionEvent e){
        nameFile = null;
        nameFile = JOptionPane.showInputDialog("Enter the name of map");
        if(nameFile != null)
        board.loadGame(nameFile+".json");
        System.out.println("successfully loaded");
    }
    
}
