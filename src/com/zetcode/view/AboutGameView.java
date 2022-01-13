package com.zetcode.view;

import javax.swing.*;
import java.awt.*;

public class AboutGameView extends HelpView{
    public AboutGameView(Container Container) {
        super(Container);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/images/page/background_aboutGame.png");
        g.drawImage(icon.getImage(), 0, 0, 1300,700, Color.black, null);
    }
}
