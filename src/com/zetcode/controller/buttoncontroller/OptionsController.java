package com.zetcode.controller.buttoncontroller;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OptionsController extends ButtonController {
    public OptionsController(Board board, JButton button) {
        super(board, button);
    }

    public void actionPerformed(ActionEvent e) {

//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(OptionsView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new OptionsView().setVisible(true));
        board.container.showOptionsView();
    }

}