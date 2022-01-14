package com.zetcode.view;

import com.zetcode.controller.KeyController;
import com.zetcode.controller.buttoncontroller.*;

import javax.swing.*;
import java.awt.*;

public class UI {
    // Frame
    public JFrame mainFrame;
    public JPanel controlPanel;

    //Button
    ImageIcon music = new ImageIcon("upArrow.png");
    public JButton addButton = new JButton("Add Room");
    public JButton addPortButton = new JButton("Add Port");
    public JButton addLiftButton = new JButton("Add Lift");

    public JButton resizeButton = new JButton("Resize");
    public JButton deleteButton = new JButton("Delete");
    public JButton drawButton = new JButton("Draw Line");
    public JButton saveButton = new JButton("Save Map");
    public JButton playButton = new JButton("Play");
    public JButton dijkstra = new JButton("Dijkstra");

    public JButton loadButton = new JButton("Load Map");

    public JButton optionsButton = new JButton("Options");
    public JButton musicButton = new JButton(music);



    //Board
    public static final Board mainBoard = new Board();


    //Controller
    ButtonController pBC = new PlayButtonController(mainBoard,playButton, drawButton);
    ButtonController dBC = new DrawButtonController(mainBoard,drawButton, playButton);

    KeyController kc = new KeyController(mainBoard);
    SaveButtonController sBC = new SaveButtonController(mainBoard, saveButton);
    LoadButtonController lBC = new LoadButtonController(mainBoard, loadButton);

    ButtonController aRC = new AddRoomController(mainBoard,addButton);

    ButtonController deleteBC = new DeleteButtonController(mainBoard,deleteButton);
    ButtonController rBC = new ResizeButtonController(mainBoard,deleteButton, playButton);
    OptionsController btn_options = new OptionsController(mainBoard, optionsButton);
    PlayMusicController mOFF = new PlayMusicController(mainBoard, musicButton);



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

        addButton.addActionListener(aRC);
        deleteButton.addActionListener(deleteBC);
        resizeButton.addActionListener(rBC);
        optionsButton.addActionListener(btn_options);
        musicButton.addActionListener(mOFF);
        
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
       // panel.add(addPortButton);
       // panel.add(addLiftButton);
        panel.add(resizeButton);
        panel.add(deleteButton);
        panel.add(drawButton);
        panel.add(playButton);

        panel.add(saveButton);
        panel.add(loadButton);

        panel.add(optionsButton);
        panel.add(musicButton);
        controlPanel.add(panel);
    }

}
