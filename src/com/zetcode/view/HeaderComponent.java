package com.zetcode.view;

import javax.swing.*;
import java.awt.*;

public class HeaderComponent {
    JFrame header = new JFrame();
    public HeaderComponent() {
        header.setVisible(true);
        header.setSize(1200,100);
        header.setBackground(Color.white);
        header.setLayout(new FlowLayout());
        ImageIcon imageIcon = new ImageIcon("src/images/panel/header.png");
        Image image = imageIcon.getImage();
        header.setIconImage(image);
    }
}
