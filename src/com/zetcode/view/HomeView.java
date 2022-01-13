package com.zetcode.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JPanel implements ActionListener {
    private final Container Container;
    private final JButton playGameButton = new JButton();
    private final JButton helpButton = new JButton();
    private final JButton optionsButton = new JButton();
    private final JButton viewButton = new JButton();
    private final JButton quitButton = new JButton();

    public HomeView(Container Container) {
        this.Container = Container;
        this.setLayout((LayoutManager)null);
        this.initCompts();
    }

    public void initCompts() {
        setFocusable(true);
        setBackground(Color.gray);
        setLayout((LayoutManager)null);
        setBounds(0, 0, 900, 700);

        ImageIcon iconPlay = new ImageIcon("src/images/page/playGameButton.png");
        playGameButton.setBounds(50, 150, 300, 80);
        playGameButton.addActionListener(this);
        playGameButton.setContentAreaFilled(false);
        playGameButton.setBorder((Border)null);
        playGameButton.setIcon(iconPlay);
        add(this.playGameButton);

        ImageIcon iconOption = new ImageIcon("src/images/page/optionsButton.png");
        optionsButton.setBounds(50, 230, 300, 80);
        optionsButton.addActionListener(this);
        optionsButton.setContentAreaFilled(false);
        optionsButton.setBorder((Border)null);
        optionsButton.setIcon(iconOption);
        add(this.optionsButton);

        ImageIcon iconView= new ImageIcon("src/images/page/viewButton.png");
        viewButton.setBounds(50, 310, 300, 80);
        viewButton.addActionListener(this);
        viewButton.setContentAreaFilled(false);
        viewButton.setBorder((Border)null);
        viewButton.setIcon(iconView);
        add(this.viewButton);

        ImageIcon iconHelp = new ImageIcon("src/images/page/helpButton.png");
        helpButton.setBounds(50, 390, 300, 80);
        helpButton.addActionListener(this);
        helpButton.setContentAreaFilled(false);
        helpButton.setBorder((Border)null);
        helpButton.setIcon(iconHelp);
        add(this.helpButton);

        ImageIcon iconQuit = new ImageIcon("src/images/page/quitButton.png");
        quitButton.setBounds(50, 470, 300, 80);
        quitButton.addActionListener(this);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorder((Border)null);
        quitButton.setIcon(iconQuit);
        add(this.quitButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.playGameButton) {
            this.Container.showPlayView();
        }
        if (e.getSource() == this.helpButton) {
            this.Container.showHelpView();
        }
        if (e.getSource() == this.optionsButton) {
            this.Container.showOptionsView();
        }
        if (e.getSource() == this.viewButton) {
            this.Container.showViewView();
        }
        if (e.getSource() == this.quitButton) {
            System.exit(0);
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/images/page/background.png");
        g.drawImage(icon.getImage(), 0, 0, 1300,700, Color.black, null);
    }
}
