package com.zetcode.model;

import javax.swing.*;
import java.awt.*;

public class Port extends Facility {

    public Port(int i, int j) {
        super(i, j);
        size_x = 90;
        size_y = 60;
        imagePath = "src/resources/images/";
        moveAbility = false;
        name = "Port";
        ID = "Port " + Integer.toString(i) + "-" + Integer.toString(j);
    }

    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        ImageIcon portImage = new ImageIcon("src/images/facilities/port.png");
        g2d.drawImage(portImage.getImage(),this.x, this.y, this.size_x, this.size_y, Color.white,null);
    }
}
