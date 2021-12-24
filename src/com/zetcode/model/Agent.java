package com.zetcode.model;

import java.awt.*;

public class Agent extends Facility {
    public Agent(int i, int j) {
        super(i, j);
        size_x = 10;
        size_y = 10;
        imagePath = "src/resources/images/";
        moveAbility = true;
        name = "Agent";
        ID = "Agent" + Integer.toString(i) + Integer.toString(j);
        color = Color.cyan;
    }
    public void drawAgent(Graphics g, int x, int y, int r) {
        x = x-(r/2);
        y = y-(r/2);
        g.fillOval(x,y,r,r);
        g.setColor(Color.cyan);
    }
}
