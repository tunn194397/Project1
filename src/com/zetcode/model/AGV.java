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
        for (Node[] nodes : nodeArray) {
            for (Node node : nodes) {
                if (node.direction == 0) {
                    if (this.getBound().intersects(node.getBound())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}




