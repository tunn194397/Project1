package com.zetcode.model;

import java.awt.*;

public class Door extends Facility {
    public Door(int i, int j) {
        super(i, j);
        size_x = 30;
        size_y = 10;
        imagePath = "src/resources/images/";
        moveAbility = false;
        name = "Door";
        ID = "Door" + Integer.toString(i) + Integer.toString(j);
        color = Color.orange;
    }

}
