package com.zetcode.view;

import com.zetcode.controller.KeyController;
import com.zetcode.controller.buttoncontroller.*;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
    //Container
    Container Container;
    // Frame
    public JPanel controlPanel = new JPanel();
    public JPanel boardPanel = new JPanel();
    public JPanel buttonPanel = new JPanel();

    //Button
    public JButton addButton = new JButton("Add Room");
    public JButton addPortButton = new JButton("Add Port");
    public JButton addLiftButton = new JButton("Add Lift");

    public JButton resizeButton = new JButton("Resize");
    public JButton deleteButton = new JButton("Delete");
    public JButton drawButton = new JButton("Draw Line");
    public JButton saveButton = new JButton("Save Map");
    public JButton playButton = new JButton("Play");

    public JButton loadButton = new JButton("Load Map");
    public JButton playAgainButton = new JButton("Play Again");
    public JButton chooseEndNodeButton = new JButton("End Node");
    public JButton optionsButton = new JButton("Options");



    //Board
    public static final Board mainBoard = new Board();


    // Table
    public JTable controlTable = new JTable(10,2);
    public JLabel collector = new JLabel("zzzzzz",10);
    public JTextField collector_x = new JTextField("");
    public JTextField collector_y = new JTextField("");

    public UI() {
        System.out.println("Start the game");
    }
    public UI (Container container) {
        this.Container = container;
        this.setLayout(null);
        showUI();
    }

    public void setUpControlPanel() {
        controlPanel.setLayout(null);
        controlPanel.setBackground(Color.lightGray);

        controlTable.setRowHeight(30);
        controlTable.setBorder(BorderFactory.createLineBorder(Color.black, 1,true));
        controlTable.setBounds(10,330,180,300);

        JPanel controlCollector = new JPanel();
        controlCollector.setBorder(BorderFactory.createLineBorder(Color.black, 1,true));
        controlCollector.setBounds(10,30,180,270);
        controlCollector.setLayout(null);

        collector.setBounds(10,10,180,100);
        controlCollector.add(collector);
        controlPanel.add(controlTable);
        controlPanel.add(controlCollector);
    }
    public void setUpBoardPanel() {
        boardPanel.setLayout(null);
        boardPanel.setBackground(Color.lightGray);

        mainBoard.setBorder(BorderFactory.createLineBorder(Color.black, 1,true));
        mainBoard.setBounds(0,30,1200,600);
        boardPanel.add(mainBoard);
    }
    public void setUpButtonPanel() {
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.lightGray);

        addButton.setBounds(10,30,70,50);
        deleteButton.setBounds(10,90,70,50);
        drawButton.setBounds(10,150,70,50);
        playButton.setBounds(10,210,70,50);
        saveButton.setBounds(10,270,70,50);
        loadButton.setBounds(10,330,70,50);
        playAgainButton.setBounds(10,390,70,50);
        chooseEndNodeButton.setBounds(10,450,70,50);
        optionsButton.setBounds(10,510,70,50);

        buttonPanel.add(addButton);
        buttonPanel.add(addPortButton);
        buttonPanel.add(addLiftButton);
        buttonPanel.add(resizeButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(drawButton);
        buttonPanel.add(playButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(playAgainButton);
        buttonPanel.add(chooseEndNodeButton);
        buttonPanel.add(optionsButton);
    }
    public void setUpAll() {
        this.setSize(1500, 700);
        this.setLayout(null);
        this.setBackground(Color.black);

        controlPanel.setBounds(0,0,200,700);
        boardPanel.setBounds(200,0,1200,700);
        buttonPanel.setBounds(1400,0,100,700);

        this.add(controlPanel);
        this.add(boardPanel);
        this.add(buttonPanel);
    }
    public void setUpButtonController () {
        //Controller
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

        playButton.addKeyListener(kc);
        playButton.addActionListener(pBC);
        drawButton.addActionListener(dBC);
        saveButton.addActionListener(sBC);
        loadButton.addActionListener(lBC);
        addButton.addActionListener(aRC);
        deleteButton.addActionListener(deleteBC);
        resizeButton.addActionListener(rBC);
        optionsButton.addActionListener(btn_options);
        playAgainButton.addActionListener(pAB);
        chooseEndNodeButton.addActionListener(cEN);
    }
    public void showUI() {
        setUpControlPanel();
        setUpBoardPanel();
        setUpButtonPanel();
        setUpAll();

        setUpButtonController();
    }
}
