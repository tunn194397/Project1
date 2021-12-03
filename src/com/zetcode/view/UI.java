package com.zetcode.view;

import com.zetcode.controller.buttoncontroller.ButtonController;
import com.zetcode.controller.buttoncontroller.DrawButtonController;
import com.zetcode.controller.buttoncontroller.LoadButtonController;
import com.zetcode.controller.buttoncontroller.PlayButtonController;
import com.zetcode.controller.buttoncontroller.PlayButtonController;
import com.zetcode.controller.buttoncontroller.SaveButtonController;
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
    public JButton saveButton = new JButton("Save Map");
    public JButton playButton = new JButton("Play");
    public JButton loadButton = new JButton("Load Map");

    //Board
    public static final Board mainBoard = new Board();

    //Controller
    ButtonController pBC = new PlayButtonController(mainBoard,playButton, drawButton);
    ButtonController dBC = new DrawButtonController(mainBoard,drawButton, playButton);
    KeyController kc = new KeyController(mainBoard);
    SaveButtonController sBC = new SaveButtonController(mainBoard, saveButton);
    LoadButtonController lBC = new LoadButtonController(mainBoard, loadButton);
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
        saveButton.addActionListener(sBC);
        loadButton.addActionListener(lBC);
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
        panel.add(playButton);
        panel.add(saveButton);
        panel.add(loadButton);
        controlPanel.add(panel);
    }

}
