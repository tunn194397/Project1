package com.zetcode.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewView extends JPanel implements ActionListener {
    private final Container Container;
    private final JButton sizeOfMapButton = new JButton();
    private final JButton themeButton = new JButton();
    private final JButton backButton = new JButton();

    public JPanel viewPanel = new JPanel();
    public String mapData = " 40 : 20 ", roomData = " 7 : 5 ", AGVData = " 1 : 0.5 ", agentData = " 0.5 : 0.5 ";

    public ViewView(Container Container) {
        this.Container = Container;
        this.setLayout((LayoutManager)null);
        this.initCompts();
    }

    public void initCompts() {
        setFocusable(true);
        setBackground(Color.gray);
        setLayout((LayoutManager)null);
        setBounds(0, 0, 900, 700);
        viewPanel.setBackground(Color.white);
        add(viewPanel);

        ImageIcon iconSizeOfMap = new ImageIcon("src/images/page/sizeOfMapButton.png");
        sizeOfMapButton.setBounds(50, 150, 300, 80);
        sizeOfMapButton.addActionListener(this);
        sizeOfMapButton.setContentAreaFilled(false);
        sizeOfMapButton.setBorder((Border)null);
        sizeOfMapButton.setIcon(iconSizeOfMap);
        add(this.sizeOfMapButton);

        ImageIcon iconTheme = new ImageIcon("src/images/page/themeButton.png");
        themeButton.setBounds(50, 230, 300, 80);
        themeButton.addActionListener(this);
        themeButton.setContentAreaFilled(false);
        themeButton.setBorder((Border)null);
        themeButton.setIcon(iconTheme);
        add(this.themeButton);

        ImageIcon iconBack = new ImageIcon("src/images/page/backButton.png");
        backButton.setBounds(80, 50, 80, 80);
        backButton.addActionListener(this);
        backButton.setContentAreaFilled(false);
        backButton.setBorder((Border)null);
        backButton.setIcon(iconBack);
        add(this.backButton);
        setUpSizeView();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.sizeOfMapButton) {
            Container.showViewView();
        }
        if (e.getSource() == this.themeButton) {
            Container.showThemeView();
        }
        if (e.getSource() == this.backButton) {
            this.Container.setShowMenu();
        }
    }

    // Set up Size View
    public void setUpSizeView() {
        String[] mapCombo = {" 40 : 20 "," 36 : 18 "," 30 : 15 ", " 20 : 10 ", " 14 : 7 "};
        JComboBox mapBox = setUpScroll(mapCombo, mapData);
        JPanel mapPanel = setUpComboBox("Size of Map: ",mapBox,0,0);

        String[] roomCombo = {" 7 : 5 ", " 6 : 4 ", " 4 : 3 "};
        JComboBox roomBox = setUpScroll(roomCombo, roomData);
        JPanel roomPanel = setUpComboBox("Size of Rooms: ", roomBox, 0,60);

        String[] AGVCombo = {" 1 : 0.5 ", " 1 : 1 ", " 2 : 1 "};
        JComboBox AGVBox = setUpScroll(AGVCombo, AGVData);
        JPanel AGVPanel = setUpComboBox("Size of AGVs: ", AGVBox, 0,120);

        String[] agentCombo = {" 0.5 : 0.5 ", " 1 : 1 "};
        JComboBox agentBox = setUpScroll(agentCombo, agentData);
        JPanel agentPanel = setUpComboBox("Size of Agents: ", agentBox, 0,180);

        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mapBox.getSelectedIndex() != -1) {
                    mapData = (String) mapBox.getItemAt(mapBox.getSelectedIndex());
                }
                if (roomBox.getSelectedIndex() != -1) {
                    roomData = (String) roomBox.getItemAt(roomBox.getSelectedIndex());
                }
                if (AGVBox.getSelectedIndex() != -1) {
                    AGVData = (String) AGVBox.getItemAt(AGVBox.getSelectedIndex());
                }
                if (agentBox.getSelectedIndex() != -1) {
                    agentData = (String) agentBox.getItemAt(agentBox.getSelectedIndex());
                }
                updateSize();
            }
        });
        applyButton.setBounds(250,300, 80,30);

        viewPanel.setBounds(350,150,800,400);
        viewPanel.setLayout(null);
        viewPanel.add(mapPanel);
        viewPanel.add(roomPanel);
        viewPanel.add(AGVPanel);
        viewPanel.add(agentPanel);
        viewPanel.add(applyButton);

        viewPanel.setBackground(Color.white);
    }

    public JComboBox setUpScroll(String[] combo, String s) {
        var model = new DefaultComboBoxModel();
        int pos = 0;
        for (int i = 0; i < combo.length; i ++) {
            model.addElement(combo[i]);
            if (combo[i].equals(s)) pos = i;
        }
        final JComboBox comboBox = new JComboBox(model);
        comboBox.setSelectedIndex(pos);
        return comboBox;
    }
    public JPanel setUpComboBox(String s, JComboBox comboBox, int x, int y) {
        JLabel label = new JLabel(s);
        label.setFont(new Font("Time New Romans",Font.BOLD,18));

        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout(null);
        tmpPanel.setBounds(x+10,y+10,700, 60);
        label.setBounds(50,20,150,30);
        JScrollPane listScrollPane = new JScrollPane(comboBox);
        listScrollPane.setBounds(200,20,300,30);
        tmpPanel.add(label);
        tmpPanel.add(listScrollPane);
        tmpPanel.setBackground(Color.white);
        return tmpPanel;
    }

    public void updateSize() {
        Container.validate.sizeOfMap = mapData;
        Container.validate.sizeOfRoom = roomData;
        Container.validate.sizeOfAGV = AGVData;
        Container.validate.sizeOfAgent = agentData;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/images/page/background_empty.png");
        g.drawImage(icon.getImage(), 0, 0, 1300,700, Color.black, null);
    }
}