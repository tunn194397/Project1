package com.zetcode.model;

import com.zetcode.view.Board;

import javax.swing.*;
import java.awt.*;

public class Facility extends JPanel{
    public int size_x, size_y, x, y;
    public int maxsize = Integer.MAX_VALUE;

    public String imagePath;
    public boolean isInFloor;
    public boolean moveAbility;
    public String name;
    public String ID;
    public ZRectangle shape;
    public Color color;

    public int getSize_x() {
        return size_x;
    }

    public void setSize_x(int size_x) {
        this.size_x = size_x;
    }

    public int getSize_y() {
        return size_y;
    }

    public void setSize_y(int size_y) {
        this.size_y = size_y;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public boolean isInFloor() {
        return isInFloor;
    }

    public void setInFloor(boolean inFloor) {
        isInFloor = inFloor;
    }

    public boolean isMoveAbility() {
        return moveAbility;
    }

    public void setMoveAbility(boolean moveAbility) {
        this.moveAbility = moveAbility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


    public Facility(int i, int j) {
        this.x = i;
        this.y = j;
    }

    public Facility() {
        ID = "Null";
    }

    public void outFloor() {
        if (this.isInFloor == true) setInFloor(false);
    }
    public void inFloor() {
        if (this.isInFloor == false) setInFloor(true);
    }

    public void switchSide() {
        int tmp = this.size_x;
        this.size_x = this.size_y;
        this.size_y= tmp;
    }
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(this.color);
        shape = new ZRectangle(this.x, this.y, this.size_x, this.size_y);
        g2d.fill(shape);
        g2d.setColor(Color.black);
        g2d.draw(shape);
        g2d.dispose();
    };
    public Rectangle getBound() {
        return new Rectangle(this.x, this.y, this.size_x, this.size_y);
    }

    public boolean checkCollision(Facility other) {
        if(this.getBound().intersects(other.getBound())) {
            return true;
        }
        return false;
    }
    public void setMovable(boolean s) {
        moveAbility = s;
    }
    public void update(int i, int j) {
        this.x = i;
        this.y = j;
    }
    public void updateSize(int i) {
        if (this.size_x*this.size_y/900 > maxsize && i > 0) {
            JOptionPane.showMessageDialog(this,this.name +" cannot resize to over the max size!","Warning resize room",JOptionPane.WARNING_MESSAGE);
        }
        else {
            this.size_x += i*(size_x/size_y);
            this.size_y += i;
        }

    }
    public void updateSize(int i, int j){
        this.size_x = i;
        this.size_y = j;
    }
}
