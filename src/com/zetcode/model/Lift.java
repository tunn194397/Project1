package com.zetcode.model;

import javax.swing.*;
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
    }
    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g.create();

        ImageIcon liftImage = new ImageIcon("src/images/facilities/lift.png");
        g2d.drawImage(liftImage.getImage(),this.x, this.y, this.size_x, this.size_y, Color.white,null);
        g2d.setColor(Color.black);
        String tmp = ((y < 300)?"U":"D") + ((x < 600)?"1":"2");
        g2d.setFont(new Font("Ariel",Font.BOLD, 18));
        g2d.drawString(tmp,(int)x + 35, (int) y + 35);
        g2d.dispose();
    }
}
