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
        backButton.setBounds(100, 50, 80, 80);
        backButton.addActionListener(this);
        backButton.setContentAreaFilled(false);
        backButton.setBorder((Border)null);
        backButton.setIcon(iconBack);
        add(this.backButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.sizeOfMapButton) {
        }
        if (e.getSource() == this.themeButton) {
        }
        if (e.getSource() == this.backButton) {
            this.Container.setShowMenu();
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/images/page/background.png");
        g.drawImage(icon.getImage(), 0, 0, 1500,700, Color.black, null);
    }
}