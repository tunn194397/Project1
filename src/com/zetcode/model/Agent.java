package com.zetcode.model;

import java.awt.*;

public class Agent extends Facility {
    public Agent(int i, int j) {
        super(i, j);
        size_x = 20;
        size_y = 20;
        imagePath = "src/resources/images/";
        moveAbility = true;
        name = "Agent";
        ID = "Agent" + Integer.toString(i) + Integer.toString(j);
        color = Color.cyan;
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.cyan);
        g2d.fillOval(this.x, this.y, this.size_x, this.size_y);
        g2d.setColor(Color.black);
        g2d.drawOval(this.x, this.y, size_x,size_y);
    }
}
