package com.zetcode.model;

import java.awt.*;

public class Node extends Rectangle {
    public int x;
    public int y;
    public final int size_x = 28;
    public final int size_y = 28;
    public Shape shape;
    public boolean isLine;

    public Node(int i, int j) {
        this.x = i;
        this.y = j;
        this.isLine = false;
    }
    public Node(){};
    public void updateNode(int i, int j) {
        this.x =i;
        this.y =j;
    }
    public void updateIsLine(boolean isLine) {
        this.isLine = isLine;
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        if (isLine) {
            g2d.setColor(Color.black);
        }
        else g2d.setColor(Color.white);
        shape = new ZRectangle(this.x, this.y, this.size_x, this.size_y);
        g2d.fill(shape);
        g2d.dispose();
    };
    public boolean isBelongTo(Facility facility){
        if (this.x > facility.x && this.y > facility.y && this.x < facility.x +facility.size_x - size_x && this.y < facility.y + facility.size_y - size_y){
            return true;
        }
        else return false;
    }
    public Rectangle getBound() {
        return new Rectangle(this.x, this.y, this.size_x, this.size_y);
    }


}
