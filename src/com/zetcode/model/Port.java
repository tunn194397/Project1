package com.zetcode.model;

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
        color = Color.blue;
    }

}
