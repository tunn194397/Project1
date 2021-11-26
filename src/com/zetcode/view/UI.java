package com.zetcode.view;

import com.zetcode.controller.ButtonController.buttonController;
import com.zetcode.controller.ButtonController.drawButtonController;
import com.zetcode.controller.ButtonController.playButtonController;
import com.zetcode.controller.KeyController;
import com.zetcode.controller.MouseController;
import com.zetcode.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class UI {
    // Frame
    public JFrame mainFrame;
    public JPanel controlPanel;

    //Button
    public JButton addButton = new JButton("Add Room");
    public JButton resizeButton = new JButton("Resize");
    public JButton deleteButton = new JButton("Delete");
    public JButton drawButton = new JButton("Draw Line");
    public JButton saveButton = new JButton("Save");
    public JButton playButton = new JButton("Play");

    //Board
    public static final Board mainBoard = new Board();

    //Controller
    buttonController pBC = new playButtonController(mainBoard,playButton, drawButton);
    buttonController dBC = new drawButtonController(mainBoard,drawButton, playButton);
    KeyController kc = new KeyController(mainBoard);

    public UI() {
        System.out.println("Start the game");
        mainFrame = new JFrame();
        mainFrame.setSize(1200, 700);
        mainFrame.setLayout(new FlowLayout());

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,1));
        mainFrame.add(controlPanel);
        mainFrame.add(mainBoard);
        mainFrame.setVisible(true);
        mainFrame.setTitle("Hospital");
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        playButton.addKeyListener(kc);
        playButton.addActionListener(pBC);
        drawButton.addActionListener(dBC);
    }

    public void showLayout() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setSize(300, 100);
        GridLayout layout = new GridLayout(1, 6);
        layout.setHgap(10);
        layout.setVgap(10);

        panel.setLayout(layout);
        panel.add(addButton);
        panel.add(resizeButton);
        panel.add(deleteButton);
        panel.add(drawButton);
        panel.add(saveButton);
        panel.add(playButton);
        controlPanel.add(panel);
    }

}
