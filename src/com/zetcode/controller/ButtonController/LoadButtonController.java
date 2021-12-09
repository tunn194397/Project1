package com.zetcode.controller.buttoncontroller;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import com.zetcode.view.Board;

public class LoadButtonController extends ButtonController{
    String nameFile;
    public LoadButtonController(Board board, JButton button) {
        super(board, button);
        
    }
    public void actionPerformed(ActionEvent e){
        nameFile = "map1.json";
        board.loadGame("map1.json");
        System.out.println("successfully loaded");
    }
    
}
