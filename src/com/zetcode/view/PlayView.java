package com.zetcode.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayView extends JPanel implements ActionListener {
    private final Container Container;
    private final JButton newMapButton = new JButton();
    private final JButton saveMapButton = new JButton();
    private final JButton saveAsButton = new JButton();
    private final JButton openMapButton = new JButton();
    private final JButton deleteButton = new JButton();
    private final JButton backButton = new JButton();

    public PlayView(Container Container) {
        this.Container = Container;
        this.setLayout((LayoutManager)null);
        this.initCompts();
    }

    public void initCompts() {
        setFocusable(true);
        setBackground(Color.gray);
        setLayout((LayoutManager)null);
        setBounds(0, 0, 900, 700);

        ImageIcon iconNew = new ImageIcon("src/images/page/newMapButton.png");
        newMapButton.setBounds(50, 150, 300, 80);
        newMapButton.addActionListener(this);
        newMapButton.setContentAreaFilled(false);
        newMapButton.setBorder((Border)null);
        newMapButton.setIcon(iconNew);
        add(this.newMapButton);

        ImageIcon iconOpen = new ImageIcon("src/images/page/openMapButton.png");
        openMapButton.setBounds(50, 230, 300, 80);
        openMapButton.addActionListener(this);
        openMapButton.setContentAreaFilled(false);
        openMapButton.setBorder((Border)null);
        openMapButton.setIcon(iconOpen);
        add(this.openMapButton);

        ImageIcon iconSave = new ImageIcon("src/images/page/saveButton.png");
        saveMapButton.setBounds(50, 310, 300, 80);
        saveMapButton.addActionListener(this);
        saveMapButton.setContentAreaFilled(false);
        saveMapButton.setBorder((Border)null);
        saveMapButton.setIcon(iconSave);
        add(this.saveMapButton);

        ImageIcon iconSaveAs= new ImageIcon("src/images/page/saveAsButton.png");
        saveAsButton.setBounds(50, 390, 300, 80);
        saveAsButton.addActionListener(this);
        saveAsButton.setContentAreaFilled(false);
        saveAsButton.setBorder((Border)null);
        saveAsButton.setIcon(iconSaveAs);
        add(this.saveAsButton);

        ImageIcon iconDelete = new ImageIcon("src/images/page/deleteButton.png");
        deleteButton.setBounds(50, 470, 300, 80);
        deleteButton.addActionListener(this);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setBorder((Border)null);
        deleteButton.setIcon(iconDelete);
        add(this.deleteButton);

        ImageIcon iconBack = new ImageIcon("src/images/page/backButton.png");
        backButton.setBounds(80, 50, 80, 80);
        backButton.addActionListener(this);
        backButton.setContentAreaFilled(false);
        backButton.setBorder((Border)null);
        backButton.setIcon(iconBack);
        add(this.backButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.newMapButton) {
            this.Container.showNewMap();
        }
        if (e.getSource() == this.openMapButton) {
            this.Container.showPlayView();
        }
        if (e.getSource() == this.saveAsButton) {
            this.Container.showPlayView();
        }
        if (e.getSource() == this.saveAsButton) {
            this.Container.showPlayView();
        }
        if (e.getSource() == this.deleteButton) {
            this.Container.showPlayView();
        }
        if (e.getSource() == this.backButton) {
            this.Container.setShowMenu();
            this.Container.isInGame = 1;
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/images/page/background.png");
        g.drawImage(icon.getImage(), 0, 0, 1300,700, Color.black, null);
    }
}