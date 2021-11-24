package com.zetcode.model;

import java.awt.*;

public class Gurney extends Facility {
    public Gurney(int i, int j) {
        super(i, j);
        size_x = 30;
        size_y = 30;
        imagePath = "src/resources/images/";
        moveAbility = true;
        name = "Gurney";
        ID = "Gurney" + Integer.toString(i) + Integer.toString(j);
        color = Color.gray;
    }
}
