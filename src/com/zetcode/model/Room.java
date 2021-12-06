package com.zetcode.model;

import java.awt.*;

public class Room extends Facility {
    public Door[] doorArray = new Door[4];
    public Room(int i, int j) {
        super(i, j);
        size_x = 210;
        size_y = 150;
        maxsize = 100;
        imagePath = "src/resources/images/";
        moveAbility = false;
        name = "Room";
        ID = "Room" + Integer.toString(i) + Integer.toString(j);
        color = Color.yellow;
        setDoor();
    }
    public void setDoor() {
        Door d1 = new Door(this.x + 25, this.y );
        Door d2 = new Door(this.x + size_x -55 , this.y );
        Door d3 = new Door(this.x + 25, this.y + this.size_y - 10);
        Door d4 = new Door(this.x + size_x -55 , this.y+ this.size_y - 10);
        this.doorArray = new Door[]{d1,d2,d3,d4};
    }
    public void draw(Graphics g) {
        super.draw(g);
        setDoor();
        for (int i = 0; i < doorArray.length; i ++) {
            doorArray[i].draw(g);
        }
    }

}
