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
    public JButton addAGVButton = new JButton("Add AGV");
    public JButton addLiftButton = new JButton("Add Lift");
    public JButton addPortButton = new JButton("Add Port");
    public JButton deleteButton = new JButton("Delete");

    public JButton drawButton = new JButton("Draw Line");
    public JButton saveButton = new JButton("");
    public JButton playButton = new JButton("Play");

    public JButton loadButton = new JButton("");
    public JButton playAgainButton = new JButton("");
    public JButton chooseEndNodeButton = new JButton("");

    public JButton optionsButton = new JButton("");
    public JButton backButton = new JButton("");

    public JButton musicButton = new JButton("CloseMusic");

    //Label
    public JLabel agvLabel = new JLabel();
    public JLabel roomLabel = new JLabel();
    public JLabel scoreLabel = new JLabel();
    public JLabel posLabel = new JLabel();
    public JLabel portLabel = new JLabel();
    public JLabel liftLabel = new JLabel();
    public JLabel mainAgvLabel = new JLabel();
    public JLabel collectorLabel = new JLabel();
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
        addButton.setBounds(10,5,100,40);
        addAGVButton.setBounds(110,5,90,40);
        addPortButton.setBounds(200,5,90,40);
        addLiftButton.setBounds(290,5,90,40);
        deleteButton.setBounds(380,5,90,40);

        agvLabel.setBounds(550,5,100,20);
        roomLabel.setBounds(550,25,100,20);
        portLabel.setBounds(650,5,100,20);
        liftLabel.setBounds(650,25,100,20);
        scoreLabel.setBounds(750,5,100,20);
        posLabel.setBounds(750,25,100,20);
        mainAgvLabel.setBounds(850,5,150,20);
        collectorLabel.setBounds(850,25,300,20);

        controlPanel.add(addButton);
        controlPanel.add(addAGVButton);
        controlPanel.add(addPortButton);
        controlPanel.add(addLiftButton);
        controlPanel.add(deleteButton);

        controlPanel.add(agvLabel);
        controlPanel.add(roomLabel);
        controlPanel.add(scoreLabel);
        controlPanel.add(posLabel);
        controlPanel.add(portLabel);
        controlPanel.add(liftLabel);
        controlPanel.add(mainAgvLabel);
        controlPanel.add(collectorLabel);
    }
    public void setUpBoardPanel() {
        boardPanel.setLayout(null);
        boardPanel.setBackground(Color.lightGray);

        mainBoard.setBounds(10,10,1200,600);
        mainBoard.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        boardPanel.add(mainBoard);
    }
    public void setUpButtonPanel() {
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.lightGray);

        musicButton.setBounds(20, 310, 40,40);
        musicButton.setContentAreaFilled(false);
        musicButton.setBorder((Border)null);
        ImageIcon icon = new ImageIcon("src/images/page/musicOn.png");
        musicButton.setIcon(new ImageIcon(icon.getImage().getScaledInstance(40,40,1)));

        buttonPanel.add(backButton);
        ImageIcon backIcon = new ImageIcon("src/images/boardButton/back.png");
        backButton.setBackground(Color.white);
        backButton.setBounds(5,10,60,40);
        backButton.setIcon(backIcon);
        backButton.addActionListener(this);

        ImageIcon saveIcon = new ImageIcon("src/images/boardButton/save.png");
        saveButton.setBackground(Color.white);
        saveButton.setBounds(5,60,60,40);
        saveButton.setIcon(saveIcon);

        ImageIcon loadIcon = new ImageIcon("src/images/boardButton/load.png");
        loadButton.setBackground(Color.white);
        loadButton.setBounds(5,110,60,40);
        loadButton.setIcon(loadIcon);

        ImageIcon drawIcon = new ImageIcon("src/images/boardButton/drawLine.png");
        drawButton.setBackground(Color.white);
        drawButton.setBounds(5,360,60,40);
        drawButton.setIcon(drawIcon);

        ImageIcon playAgainIcon = new ImageIcon("src/images/boardButton/playAgain.png");
        playAgainButton.setBackground(Color.white);
        playAgainButton.setBounds(5,410,60,40);
        playAgainButton.setIcon(playAgainIcon);

        ImageIcon endIcon = new ImageIcon("src/images/boardButton/endNode.png");
        chooseEndNodeButton.setBackground(Color.white);
        chooseEndNodeButton.setBounds(5,460,60,40);
        chooseEndNodeButton.setIcon(endIcon);

        ImageIcon optionIcon = new ImageIcon("src/images/boardButton/options.png");
        optionsButton.setBackground(Color.white);
        optionsButton.setBounds(5,510,60,40);
        optionsButton.setIcon(optionIcon);

        ImageIcon playIcon = new ImageIcon("src/images/boardButton/play.png");
        playButton.setBackground(Color.white);
        playButton.setBounds(5,560,60,50);
        playButton.setIcon(playIcon);

        buttonPanel.add(backButton);
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
        ButtonController pAB = new PlayAgainController(mainBoard,playAgainButton, drawButton);
        ButtonController cEN = new ChooseEndNodeController(mainBoard,chooseEndNodeButton);
        OptionsController btn_options = new OptionsController(mainBoard, optionsButton);
        PlayMusicController pMC = new PlayMusicController(mainBoard, musicButton);

        AddAGVController aac = new AddAGVController(mainBoard,addAGVButton);
        AddPortController apc = new AddPortController(mainBoard,addPortButton);
        AddLiftController alc = new AddLiftController(mainBoard, addLiftButton);

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

        addAGVButton.addActionListener(aac);
        addPortButton.addActionListener(apc);
        addLiftButton.addActionListener(alc);

        playButton.setToolTipText("Play");
        drawButton.setToolTipText("Draw Line");
        saveButton.setToolTipText("Save Map");
        loadButton.setToolTipText("Load Map");
        optionsButton.setToolTipText("Options");
        playAgainButton.setToolTipText("Play Again");
        chooseEndNodeButton.setToolTipText("Choose End Node");
        backButton.setToolTipText("Back");

    }
    public void setUpAll() {
        controlPanel.setBounds(0,611,1210,90);
        boardPanel.setBounds(0,1,1210,610);
        buttonPanel.setBounds(1210,0,90,700);
        this.add(controlPanel);
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
    public void showLabel() {
        agvLabel.setFont(new Font("Time New Romans",Font.BOLD,13));
        roomLabel.setFont(new Font("Time New Romans",Font.BOLD,13));
        scoreLabel.setFont(new Font("Time New Romans",Font.BOLD,13));
        posLabel.setFont(new Font("Time New Romans",Font.BOLD,13));
        portLabel.setFont(new Font("Time New Romans",Font.BOLD,13));
        liftLabel.setFont(new Font("Time New Romans",Font.BOLD,13));
        mainAgvLabel.setFont(new Font("Time New Romans",Font.BOLD,13));
        collectorLabel.setFont(new Font("Time New Romans",Font.BOLD,13));

        agvLabel.setText("AGV:   " + mainBoard.agvArray.size());
        roomLabel.setText("Room:   " + mainBoard.roomArray.size());
        scoreLabel.setText("Score:   " + 0);
        posLabel.setText("Positive:   "+ mainBoard.pos);
        portLabel.setText("Port:    " + mainBoard.portArray.size());
        liftLabel.setText("Lift:   " + mainBoard.liftArray.size());
        mainAgvLabel.setText("Main AGV: "+ mainBoard.mainAGV.x + " - " + mainBoard.mainAGV.y);
        collectorLabel.setText("Collector:  " + ((!mainBoard.collector.ID.equals("Null"))? (mainBoard.collector.name + " [" + mainBoard.collector.x + " - " + mainBoard.collector.y + "]" ):""));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.backButton) {
            this.container.showPlayView();
        }
    }
}