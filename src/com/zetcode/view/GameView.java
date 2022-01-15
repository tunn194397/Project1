package com.zetcode.view;

import com.zetcode.controller.KeyController;
import com.zetcode.controller.buttoncontroller.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JPanel implements ActionListener {
    // Container
    Container container;
    // Frame
    public JPanel controlPanel = new JPanel();
    public JPanel boardPanel = new JPanel();
    public JPanel buttonPanel = new JPanel();

    //Button
    public JButton addButton = new JButton("Add Room");
    public JButton deleteButton = new JButton("Delete");
    public JButton drawButton = new JButton("Draw Line");
    public JButton saveButton = new JButton("Save Map");
    public JButton playButton = new JButton("Play");

    public JButton loadButton = new JButton("Load Map");
    public JButton playAgainButton = new JButton("Play Again");
    public JButton chooseEndNodeButton = new JButton("End Node");

    public JButton optionsButton = new JButton("Options");
    public JButton backButton = new JButton("Back");
    public JButton musicButton = new JButton("CloseMusic");

    //Board
    public static Board mainBoard;

    //Controller



    public GameView() {
        System.out.println("Start the game");
        this.setSize(1300, 700);
        this.setLayout(null);

        boardPanel.setBounds(0,0,1200,700);
        buttonPanel.setBounds(1200,0,100,700);
        this.add(boardPanel);
        this.add(buttonPanel);

    }
    public GameView(Container container) {
        this.container = container;
        this.setSize(1300, 700);
        this.setLayout(null);
        mainBoard = new Board(container);
        showUI();
    }

    public void setUpControlPanel() {
        controlPanel.setLayout(null);
        controlPanel.setBackground(Color.lightGray);

    }
    public void setUpBoardPanel() {
        boardPanel.setLayout(null);
        boardPanel.setBackground(Color.lightGray);

        mainBoard.setBounds(0,30,1200,600);
        mainBoard.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        boardPanel.add(mainBoard);
    }
    public void setUpButtonPanel() {
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.lightGray);

        addButton.setBounds(10,20,70,40);
        deleteButton.setBounds(10,80,70,40);
        drawButton.setBounds(10,140,70,40);
        playButton.setBounds(10,200,70,40);
        saveButton.setBounds(10,260,70,40);
        loadButton.setBounds(10,320,70,40);
        playAgainButton.setBounds(10,380,70,40);
        chooseEndNodeButton.setBounds(10,440,70,40);
        optionsButton.setBounds(10,500,70,40);

        backButton.setBounds(10, 560, 70, 40);
        backButton.addActionListener(this);
        musicButton.setBounds(20, 610, 50,50);

        musicButton.setContentAreaFilled(false);
        musicButton.setBorder((Border)null);
        ImageIcon icon = new ImageIcon("src/images/page/musicOn.png");
        musicButton.setIcon(icon);

        buttonPanel.add(backButton);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(drawButton);
        buttonPanel.add(playButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(playAgainButton);
        buttonPanel.add(chooseEndNodeButton);
        buttonPanel.add(optionsButton);
        buttonPanel.add(musicButton);
    }
    public void setUpButtonController() {
        ButtonController pBC = new PlayButtonController(mainBoard,playButton, drawButton);
        ButtonController dBC = new DrawButtonController(mainBoard,drawButton, playButton);

        KeyController kc = new KeyController(mainBoard);
        SaveButtonController sBC = new SaveButtonController(mainBoard, saveButton);
        LoadButtonController lBC = new LoadButtonController(mainBoard, loadButton);

        ButtonController aRC = new AddRoomController(mainBoard,addButton);

        ButtonController deleteBC = new DeleteButtonController(mainBoard,deleteButton);
        ButtonController rBC = new ResizeButtonController(mainBoard,deleteButton, playButton);
        ButtonController pAB = new PlayAgainController(mainBoard,playAgainButton, drawButton);
        ButtonController cEN = new ChooseEndNodeController(mainBoard,chooseEndNodeButton);
        OptionsController btn_options = new OptionsController(mainBoard, optionsButton);
        PlayMusicController pMC = new PlayMusicController(mainBoard, musicButton);
        playButton.addKeyListener(kc);
        playButton.addActionListener(pBC);
        drawButton.addActionListener(dBC);
        saveButton.addActionListener(sBC);
        loadButton.addActionListener(lBC);

        addButton.addActionListener(aRC);
        deleteButton.addActionListener(deleteBC);
        optionsButton.addActionListener(btn_options);
        playAgainButton.addActionListener(pAB);
        chooseEndNodeButton.addActionListener(cEN);
        musicButton.addActionListener(pMC);
    }
    public void setUpAll() {
//        controlPanel.setBounds(0,0,200,700);
        boardPanel.setBounds(0,1,1200,700);
        buttonPanel.setBounds(1200,0,100,700);
//        this.add(controlPanel);
        this.add(boardPanel);
        this.add(buttonPanel);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }
    public void showUI() {
        setUpControlPanel();
        setUpBoardPanel();
        setUpButtonPanel();
        setUpButtonController();

        setUpAll();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.backButton) {
            this.container.showPlayView();
        }
    }
}