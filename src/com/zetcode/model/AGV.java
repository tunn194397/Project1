package com.zetcode.model;

import java.awt.*;

public class AGV extends Facility {

    public AGV(int i, int j) {
        super(i, j);
        size_x = 30;
        size_y = 15;
        imagePath = "src/resources/images/";
        moveAbility = true;
        name = "AGV";
        ID = "AGV" + Integer.toString(i) + Integer.toString(j);
        color = Color.red;
    }

    public boolean checkCollisionWithNode(Node[][] nodeArray) {
        for (int i = 0; i < nodeArray.length; i ++) {
            for (int j =0; j < nodeArray[i].length; j ++) {
                if (!nodeArray[i][j].isLine) {
                    if (this.getBound().intersects(nodeArray[i][j].getBound())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}




