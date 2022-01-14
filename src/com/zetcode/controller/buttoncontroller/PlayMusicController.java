package com.zetcode.controller.buttoncontroller;

import javax.swing.JButton;

import com.zetcode.model.Facility;
import com.zetcode.view.Board;
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
            button.setText("Open Music");
            board.turnOffMusic();
            board.soundIsPlay = false;
        }
        else {
            board.turnOnMusic(1);
            button.setText("Close Music");
            board.soundIsPlay = true;
        }
    }
}
