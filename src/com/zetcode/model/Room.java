package com.zetcode.model;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Room extends Facility {
    public Door[] doorArray = new Door[4];
    public Vector<Agent> agentArray = new Vector<>();

    int[] xArray = {45, 75, 105, 30, 60, 90, 120, 45, 75, 105};
    int[] yArray = {30, 30, 30, 60, 60, 60, 60, 90, 90, 90};
    List<Integer> random = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    public Room(int i, int j, int agent) {
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
        setAgent(agent);
    }

    public void setDoor() {
        Door d1 = new Door(this.x + 25, this.y );
        Door d2 = new Door(this.x + size_x -55 , this.y );
        Door d3 = new Door(this.x + 25, this.y + this.size_y - 10);
        Door d4 = new Door(this.x + size_x -55 , this.y+ this.size_y - 10);
        this.doorArray = new Door[]{d1,d2,d3,d4};
    }

    public void setAgent(int agent) {
        Random rd = new Random();
        Collections.shuffle(random);
        int realAgent = rd.nextInt(agent);
        for (int i = 0; i < realAgent; i++) {
            int a = random.get(i);
            Agent a1 = new Agent(this.x + xArray[a], this.y + yArray[a]);
            this.agentArray.add(a1);
        }
    }

    public void moveAgent() {
        for (int i = 0; i < agentArray.size(); i++) {
            agentArray.remove(0);
            int a = random.get(i);
            Agent a1 = new Agent(this.x + xArray[a], this.y + yArray[a]);
            agentArray.add(a1);
        }
    }

    public void draw(Graphics g) {
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        ImageIcon roomImage = new ImageIcon("src/images/facilities/room.png");
        g2d.drawImage(roomImage.getImage(),this.x, this.y, this.size_x, this.size_y, Color.white,null);
        setDoor();
        for (int i = 0; i < doorArray.length; i++) {
            doorArray[i].draw(g);
        }
        moveAgent();
        for (int i = 0; i < agentArray.size(); i++) {
            agentArray.get(i).draw(g);
        }
    }
}