package com.zetcode.model;

import com.zetcode.model.Facility;

import java.awt.*;

public class Person extends Facility {
    public Person(int i, int j) {
        super(i, j);
        size_x = 20;
        size_y = 30;
        imagePath = "src/resources/images/";
        moveAbility = true;
        name = "Person";
        ID = "Person" + Integer.toString(i) + Integer.toString(j);
    }
    public void personGraphic(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(this.x, this.y, this.size_x, this.size_y);
    }
}
