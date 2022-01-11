package com.zetcode.model;

import com.zetcode.configuration.Config;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Room extends Facility {
    public Door[] doorArray = new Door[4];
    public Agent[] agentArray = new Agent[Config.getAgentRoom()];
    public int agentNum = ThreadLocalRandom.current().nextInt(0, Config.getAgentRoom() + 1);

    public Room(int i, int j) {
        super(i, j);
        size_x = 210;
        size_y = 150;
        maxsize = 100;
        imagePath = "src/resources/images/";
        moveAbility = false;
        name = "Room";
        ID = "Room" + Integer.toString(i) + Integer.toString(j);
        color = Color.white;
        setDoor();
        agentPosInRoom();
    }

    public void setDoor() {
        Door d1 = new Door(this.x + 25, this.y );
        Door d2 = new Door(this.x + size_x -55 , this.y );
        Door d3 = new Door(this.x + 25, this.y + this.size_y - 10);
        Door d4 = new Door(this.x + size_x -55 , this.y+ this.size_y - 10);
        this.doorArray = new Door[]{d1,d2,d3,d4};
    }

    public void setAgent() {
        Agent a1 = new Agent(this.x + 30, this.y+60);
        this.agentArray = new Agent[]{a1};
    }

    public void agentPosInRoom() {
        for (int i = 0; i < agentNum; i++) {
            int a_pos_x = ThreadLocalRandom.current().nextInt( this.x + 10, this.x + 200 + 1);
            int a_pos_y = ThreadLocalRandom.current().nextInt(this.y +10, this.y + 140 + 1);
            this.agentArray[i] = new Agent(a_pos_x, a_pos_y);
        }
    }

    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        ImageIcon roomImage = new ImageIcon("src/images/facilities/room.png");
        g2d.drawImage(roomImage.getImage(),this.x, this.y, this.size_x, this.size_y, Color.white,null);
        setDoor();
        for (int i = 0; i < doorArray.length; i ++) {
            doorArray[i].draw(g);
        }
        setAgent();
        for (int i = 0; i < agentArray.length; i ++) {
            agentArray[i].draw(g);
        }
    }

}
