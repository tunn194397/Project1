package com.zetcode.view;

import javax.swing.*;
import java.awt.*;

public class AboutTeamView extends HelpView {
    public AboutTeamView(Container Container) {
        super(Container);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon("src/images/page/background_aboutTeam.png");
        g.drawImage(icon.getImage(), 0, 0, 1300,700, Color.black, null);
    }
}
