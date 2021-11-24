package com.zetcode.model;

import java.awt.*;

public class Lift extends Facility {
    public Boolean isUp;
    public Lift(int i, int j) {
        super(i, j);
        size_x = 90;
        size_y = 60;
        imagePath = "src/resources/images/";
        moveAbility = false;
        name = "Lift";
        ID = "Lift" + Integer.toString(i) + Integer.toString(j);
        color = Color.green;
        isUp = (y < 300)? true: false;
    }
    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.black);
        g2d.setFont(g2d.getFont().deriveFont(10f));
        String tmp = ((this.isUp)?"U":"D") + ((x < 600)?"1":"2");
        g2d.drawString(tmp,(int)x + size_x/3, (int) y + size_y/3);
        g2d.dispose();
    }
}
