package com.zetcode.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThemeView extends JPanel implements ActionListener {
    private final Container Container;
    private final JButton sizeOfMapButton = new JButton();
    private final JButton themeButton = new JButton();
    private final JButton backButton = new JButton();

    public JPanel viewPanel = new JPanel();
    public JButton grayTheme = new JButton();
    public JButton darkTheme = new JButton();
    public JButton lightTheme = new JButton();

    public int theme = 1;

    public ThemeView(Container Container) {
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
        setUpThemeView();
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
    public void setUpThemeView() {
        borderButton();

        ImageIcon iconLight = new ImageIcon("src/images/page/lightMode.png");
        lightTheme.setIcon(new ImageIcon(iconLight.getImage().getScaledInstance(300,200,1)));
        lightTheme.setBounds(0,50,300,200);
        lightTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theme = 1;
                borderButton();
            }
        });

        ImageIcon iconDark = new ImageIcon("src/images/page/darkMode.png");
        darkTheme.setIcon(new ImageIcon(iconDark.getImage().getScaledInstance(300,200,1)));
        darkTheme.setBounds(300,50,300,200);
        darkTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theme = 2;
                borderButton();
            }
        });

        ImageIcon iconGray = new ImageIcon("src/images/page/grayMode.png");
        grayTheme.setIcon(new ImageIcon(iconGray.getImage().getScaledInstance(300,200,1)));
        grayTheme.setBounds(600,50,300,200);
        grayTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theme = 3;
                borderButton();
            }
        });
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container.validate.theme = theme;
            }
        });
        applyButton.setBounds(250,300, 80,30);
        viewPanel.setBounds(350,150,900,400);
        viewPanel.setLayout(null);
        viewPanel.add(applyButton);
        viewPanel.add(lightTheme);
        viewPanel.add(grayTheme);
        viewPanel.add(darkTheme);

        viewPanel.setBackground(Color.white);
    }

    public void borderButton(){
        if (theme == 1) {
            lightTheme.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            darkTheme.setBorder(null);
            grayTheme.setBorder(null);
        } else {
            if (theme == 2) {
                darkTheme.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                lightTheme.setBorder(null);
                grayTheme.setBorder(null);
            }
            else if (theme == 3){
                grayTheme.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                lightTheme.setBorder(null);
                darkTheme.setBorder(null);
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/images/page/background_empty.png");
        g.drawImage(icon.getImage(), 0, 0, 1300,700, Color.black, null);
    }
}
