package com.zetcode;

import com.zetcode.model.Facility;
import com.zetcode.model.Lift;
import com.zetcode.model.Port;
import com.zetcode.model.Room;
import com.zetcode.view.Board;
import com.zetcode.view.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Main extends JFrame {
    public UI newUI = new UI();
    public Main() {
        initUI();
    }
    private void initUI() {
        newUI.showLayout();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Main().newUI.mainFrame;
            ex.setVisible(true);
        });
    }

}
