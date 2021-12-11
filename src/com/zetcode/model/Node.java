package com.zetcode.model;

import javax.swing.*;
import java.awt.*;

public class Node extends Rectangle {
    public int x;
    public int y;
    public final int size_x = 30;
    public final int size_y = 30;
    public Shape shape;
    public int direction; //0 la khong trong duong cua AGV, 1 la phai, 2 la trai, 3 la len, 4 la xuong, 5 la first, 6 la last
    public boolean isNull;
    public boolean hasNode; // Là giá trị mà node đó có AGV đi qua hay không true: có, false: không có. những node nào không phải là đường của AGV (direction = 0) thì hasNode =  false;

    public int coordinate_x, coordinate_y;
    public Node Up, Down, Right, Left ; //Bốn node cho biết các node xung quanh của node này.
    public int up, down, right, left; // Bốn giá trị đại diện cho giá trị các edge đến các node xung quanh của nó.
    public int w, u; // Hai giá trị biểu thị cho giá trị dự đoán và giá trị thực thời gian AGV phải dừng.

    public Node(int i, int j) {
        this.x = i;
        this.y = j;
        this.direction = 0;
        this.isNull = false;
        this.coordinate_x = i/30;
        this.coordinate_y = j/30;
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
    public int getW() {
        return w;
    }
    public void setW(int w) {
        this.w = w;
    }
    public int getU() {
        return u;
    }
    public void setU(int u) {
        this.u = u;
    }
    public void setEdge() {
        this.up = (this.Up == null)? -1: 1 ;
        this.down = (this.Down == null)? -1: 1;
        this.right = (this.Right == null)? -1: 1;
        this.left = (this.Left == null)? -1: 1 ;
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
        return ((this.x >= facility.x - size_x-3) && (this.y >= facility.y - size_y-3) && (this.x <= facility.x + facility.size_x+3) && (this.y <= facility.y + facility.size_y+3));
    }
    public Rectangle getBound() {
        return new Rectangle(this.x, this.y, this.size_x, this.size_y);
    }

}
