package com.zetcode.model;

import javax.swing.*;
import java.awt.*;

public class Node extends Rectangle {
    public int x;
    public int y;
    public final int size_x = 28;
    public final int size_y = 28;
    public Shape shape;
    public int direction; //0 la khong trong duong cua AGV, 1 la phai, 2 la trai, 3 la len, 4 la xuong, 5 la first, 6 la last
    public boolean isNull;

    public Node(int i, int j) {
        this.x = i;
        this.y = j;
        this.direction = 0;
        this.isNull = false;
    }
    public Node(){
        this.isNull = true;
    };
    public void updateNode(int i, int j) {
        this.x =i;
        this.y =j;
    }
    public void updateDirection(int direction) {
        this.direction = direction;
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        ImageIcon imageArrow = new ImageIcon();
            switch (direction) {
                case 0 -> g2d.setColor(Color.white);
                case 1 -> {
                    g2d.setColor(Color.black);
                    imageArrow = new ImageIcon("src/images/arrow/rightArrow.png");
                }
                case 2 -> {
                    g2d.setColor(Color.black);
                    imageArrow = new ImageIcon("src/images/arrow/leftArrow.png");
                }
                case 3 -> {
                    g2d.setColor(Color.black);
                    imageArrow = new ImageIcon("src/images/arrow/upArrow.png");
                }
                case 4 -> {
                    g2d.setColor(Color.black);
                    imageArrow = new ImageIcon("src/images/arrow/downArrow.png");
                }
                case 5 -> {
                    g2d.setColor(Color.orange);
                }
                default -> g2d.setColor(Color.white);
            }
        shape = new ZRectangle(this.x, this.y, this.size_x, this.size_y);
        g2d.fill(shape);
        if (imageArrow != null) {
            g2d.drawImage(imageArrow.getImage(),this.x+this.size_x/3, this.y+this.size_y/4, this.size_x/3, this.size_y/2, Color.yellow,null);
        }
        g2d.dispose();
    };
    public boolean isBelongTo(Facility facility){
        return ((this.x >= facility.x) && (this.y >= facility.y) && (this.x <= facility.x + facility.size_x - size_x) && (this.y <= facility.y + facility.size_y - size_y));
    }
    public Rectangle getBound() {
        return new Rectangle(this.x, this.y, this.size_x, this.size_y);
    }

}
