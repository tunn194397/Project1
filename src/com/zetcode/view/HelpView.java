package com.zetcode.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpView extends JPanel implements ActionListener {
    private final Container container;
    private final JButton howToPlayButton = new JButton();
    private final JButton aboutGameButton = new JButton();
    private final JButton aboutTeamButton = new JButton();
    private final JButton backButton = new JButton();

    public HelpView(Container Container) {
        this.container = Container;
        this.setLayout((LayoutManager)null);
        this.initCompts();
    }

    public void initCompts() {
        setFocusable(true);
        setBackground(Color.gray);
        setLayout((LayoutManager)null);
        setBounds(0, 0, 900, 700);

        ImageIcon iconHowToPlay = new ImageIcon("src/images/page/howToPlayButton.png");
        howToPlayButton.setBounds(50, 150, 300, 80);
        howToPlayButton.addActionListener(this);
        howToPlayButton.setContentAreaFilled(false);
        howToPlayButton.setBorder((Border)null);
        howToPlayButton.setIcon(iconHowToPlay);
        add(this.howToPlayButton);

        ImageIcon iconAboutGame = new ImageIcon("src/images/page/aboutGameButton.png");
        aboutGameButton.setBounds(50, 230, 300, 80);
        aboutGameButton.addActionListener(this);
        aboutGameButton.setContentAreaFilled(false);
        aboutGameButton.setBorder((Border)null);
        aboutGameButton.setIcon(iconAboutGame);
        add(this.aboutGameButton);

        ImageIcon iconAboutTeam = new ImageIcon("src/images/page/aboutTeamButton.png");
        aboutTeamButton.setBounds(50, 310, 300, 80);
        aboutTeamButton.addActionListener(this);
        aboutTeamButton.setContentAreaFilled(false);
        aboutTeamButton.setBorder((Border)null);
        aboutTeamButton.setIcon(iconAboutTeam);
        add(this.aboutTeamButton);

        ImageIcon iconBack = new ImageIcon("src/images/page/backButton.png");
        backButton.setBounds(80, 50, 80, 80);
        backButton.addActionListener(this);
        backButton.setContentAreaFilled(false);
        backButton.setBorder((Border)null);
        backButton.setIcon(iconBack);
        add(this.backButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.howToPlayButton) {
            container.showHelpView();
        }
        if (e.getSource() == this.aboutGameButton) {
            container.showAboutGameView();
        }
        if (e.getSource() == this.aboutTeamButton) {
            container.showAboutTeamView();
        }
        if (e.getSource() == this.backButton) {
            this.container.setShowMenu();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/images/page/background_howToPlay.png");
        g.drawImage(icon.getImage(), 0, 0, 1300,700, Color.black, null);
    }
}
