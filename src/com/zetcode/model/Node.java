package com.zetcode.model;

import javax.swing.*;
import java.awt.*;

public class Node extends Rectangle {
    public int x;
    public int y;
    public final int size_x = 30;
    public final int size_y = 30;
    public String ID;
    public Shape shape;
    public boolean isNull;
    public boolean isHead;
    public boolean isLine;

    public boolean isBlocked = false; // Là giá trị mà node đó có AGV đi qua hay không true: có, false: không có. những node nào không phải là đường của AGV (direction = 0) thì hasNode =  false;
    public int agvPriority = 0;

    public int coordinate_x, coordinate_y;
    public Node Up, Down, Right, Left ; //Bốn node cho biết các node xung quanh của node này.
    public Direction direction = new Direction();  // Bốn giá trị đại diện cho giá trị các edge đến các node xung quanh của nó.
    public float w = 0.0F, u = 0.0F; // Hai giá trị biểu thị cho giá trị dự đoán và giá trị thực thời gian AGV phải dừng.

    public Node(int i, int j) {
        this.x = i;
        this.y = j;
        this.isLine = false;
        this.isNull = false;
        this.coordinate_x = i/30;
        this.coordinate_y = j/30;
        this.direction = new Direction();
        this.isHead = false;
        this.ID = i/30 + " - " + j/30;
        this.isBlocked = false;
    }
    public Node(){
        this.isNull = true;
        this.agvPriority = 0;
        this.isBlocked = false;
    };
    public void updateNode(int i, int j) {
        this.x = i;
        this.y = j;
    }
    public void updateDirection(String s) {
        this.isLine = true;
        if (s.equals("head")) isHead = true;
        else {
            if (s.equals("up")) direction.updateUp(1);
            if (s.equals("down")) direction.updateDown(1);
            if (s.equals("right")) direction.updateRight(1);
            if (s.equals("left")) direction.updateLeft(1);
            this.isHead = false;
        }
    }
    public void updateIsLine(boolean isLine) {
        if (isLine) this.isLine = true;
        else {
            this.isLine = false;
            this.isHead = false;
            direction.updateAll(0,0,0,0);
        }
    }

    public Node getUp() {
        return Up;
    }
    public void setUp(Node up) {
        Up = up;
    }
    public Node getDown() {
        return Down;
    }
    public void setDown(Node down) {
        Down = down;
    }
    public Node getRight() {
        return Right;
    }
    public void setRight(Node right) {
        Right = right;
    }
    public Node getLeft() {
        return Left;
    }
    public void setLeft(Node left) {
        Left = left;
    }
    public float getW() {
        return w;
    }
    public void setW(float w) {
        this.w = w;
    }
    public float getU() {
        return u;
    }
    public void setU(int u) {
        this.u = u;
    }

    public void setEdge() {
        direction.updateAll((Up == null)?0:1, (Down == null)?0:1, (Right == null)?0:1, (Left == null)?0:1);
    }
    public boolean getIsLine() { return isLine; }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        ImageIcon imageArrow = new ImageIcon();
        if (isLine) {
            g2d.setColor(Color.black);
            if (direction.up == 1) imageArrow = new ImageIcon("src/images/arrow/upArrow.png");
            if (direction.down == 1) imageArrow = new ImageIcon("src/images/arrow/downArrow.png");
            if (direction.left == 1) imageArrow = new ImageIcon("src/images/arrow/leftArrow.png");
            if (direction.right == 1) imageArrow = new ImageIcon("src/images/arrow/rightArrow.png");
            if (isHead) g2d.setColor(Color.yellow);
        }
        else {
            g2d.setColor(Color.white);
            direction.updateAll(0,0,0,0);
        }
        shape = new ZRectangle(this.x, this.y, this.size_x, this.size_y);
        g2d.fill(shape);
        if (imageArrow != null) {
            g2d.drawImage(imageArrow.getImage(),this.x+this.size_x/3, this.y+this.size_y/4, this.size_x/3, this.size_y/2, Color.yellow,null);        }
        if (isLine) {
            g.drawString(String.valueOf(w), this.x + 10, this.y + 10);
        }
        //Cho nay de test xem o co bi block ko
//        if (agvPriority != 0) {
//            g2d.setColor(Color.gray);
//            g2d.fill(shape);
//        }
        g2d.dispose();
    };
    public boolean isBelongTo(Facility facility){
        return ((this.x >= facility.x - size_x-3) && (this.y >= facility.y - size_y-3) && (this.x <= facility.x + facility.size_x+3) && (this.y <= facility.y + facility.size_y+3));
    }
    public Rectangle getBound() {
        return new Rectangle(this.x, this.y, this.size_x, this.size_y);
    }

    public void updateDelayTime() {
        if (u == 0) {
            u = 1.0F;
        }
        else u ++;
        w = (float) (0.6* w + 0.4*u);
    }
    public void updateIsBlocked(boolean b) {
        isBlocked = b;
        updateDelayTime();
    }
    @Override
    public boolean equals(Object other) {
        return  (this.ID.equals(((Node)other).ID));
    }
    public void setAgvPriority(AGV agv) {
        this.agvPriority = agv.priority;
    }
    public void freeNode() {
        agvPriority = 0;
        isBlocked = false;
    }
}
