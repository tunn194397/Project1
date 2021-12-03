package com.zetcode.controller.buttoncontroller;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import com.zetcode.view.Board;

public class LoadButtonController extends ButtonController{

    public LoadButtonController(Board board, JButton button) {
        super(board, button);
        
    }
    public void actionPerformed(ActionEvent e){
        board.loadGame();
    }
    
}
