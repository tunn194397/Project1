package com.zetcode.controller.buttoncontroller;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PlayMusicController extends ButtonController {
    public JButton musicButton;
    public PlayMusicController(Board board, JButton button) {
        super(board, button);
    }
    /*
    public PlayMusicController(Board board, JButton button, JButton musicButton) {
        super(board, button);
        this.musicButton = musicButton;
    }
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (board.soundIsPlay){
            ImageIcon icon = new ImageIcon("src/images/page/musicOff.png");
            button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40,40,1)));
            board.turnOffMusic();
            board.soundIsPlay = false;
        }
        else {
            board.turnOnMusic(1);
            ImageIcon icon = new ImageIcon("src/images/page/musicOn.png");
            button.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40,40,1)));
            board.soundIsPlay = true;
        }
    }
}