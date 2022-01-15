package com.zetcode.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScoreView extends JPanel implements ActionListener {
    private final Container Container;
    private final JButton newMapButton = new JButton();
    private final JButton userButton = new JButton();
    private final JButton highScoreButton = new JButton();
    private final JButton backButton = new JButton();

    public HighScoreView(Container Container) {
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


        ImageIcon iconUser = new ImageIcon("src/images/page/userButton.png");
        userButton.setBounds(50, 230, 300, 80);
        userButton.addActionListener(this);
        userButton.setContentAreaFilled(false);
        userButton.setBorder((Border)null);
        userButton.setIcon(iconUser);
        add(this.userButton);


        ImageIcon iconHighScore = new ImageIcon("src/images/page/highScoreButton.png");
        highScoreButton.setBounds(50, 470, 300, 80);
        highScoreButton.addActionListener(this);
        highScoreButton.setContentAreaFilled(false);
        highScoreButton.setBorder((Border)null);
        highScoreButton.setIcon(iconHighScore);
        add(this.highScoreButton);

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
        if (e.getSource() == this.userButton) {
            this.Container.showPlayerView();
        }
        if (e.getSource() == this.highScoreButton) {
            this.Container.showHighScoreView();
        }
        if (e.getSource() == this.backButton) {
            this.Container.setShowMenu();
            this.Container.isInGame = 1;
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/images/page/background_empty.png");
        g.drawImage(icon.getImage(), 0, 0, 1300,700, Color.black, null);
    }
}
