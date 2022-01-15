package com.zetcode.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Node extends Rectangle implements Comparable<Node> {
    public int x;
    public int y;
    public final int size_x = 30;
    public final int size_y = 30;
    public String ID;
    public Shape shape;
    public boolean isNull;
    public boolean isHead;
    public boolean isLine;
    public boolean isEndNode = false;
    public boolean isCover = false;

    public boolean isBlocked = false; // Là giá trị mà node đó có AGV đi qua hay không true: có, false: không có. những node nào không phải là đường của AGV (direction = 0) thì hasNode =  false;
    public int agvPriority = 0;

    public int coordinate_x, coordinate_y;
    public Node Up, Down, Right, Left ; //Bốn node cho biết các node xung quanh của node này.
    public Direction direction = new Direction();  // Bốn giá trị đại diện cho giá trị các edge đến các node xung quanh của nó.
    public float w = 0.0F, u = 0.0F; // Hai giá trị biểu thị cho giá trị dự đoán và giá trị thực thời gian AGV phải dừng.

    // Nhung thuoc tinh can thiet cho tim duong di ngan nhan
    public boolean visited;
    public float dist = 1000;
    public Node pr;
    public List<Edge> list = new ArrayList<>();

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
        this.ID = "Null";
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
        addNeighbour(new Edge(this, up));
    }
    public Node getDown() {
        return Down;
    }
    public void setDown(Node down) {
        Down = down;
        addNeighbour(new Edge(this, down));
    }
    public Node getRight() {
        return Right;
    }
    public void setRight(Node right) {
        Right = right;
        addNeighbour(new Edge(this, right));
    }
    public Node getLeft() {
        return Left;
    }
    public void setLeft(Node left) {
        Left = left;
        addNeighbour(new Edge(this, left));
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
            if (isHead) g2d.setColor(Color.yellow);
            if (isEndNode) g2d.setColor(Color.orange);
        }
        else {
            g2d.setColor(Color.white);
            direction.updateAll(0,0,0,0);
        }
        shape = new ZRectangle(this.x, this.y, this.size_x, this.size_y);
        g2d.fill(shape);

        if (isLine && !isEndNode) {
            if (direction.up == 1) {
                imageArrow = new ImageIcon("src/images/arrow/up.png");
                g2d.drawImage(imageArrow.getImage(),this.x+25, this.y + 5, 5,20,Color.yellow, null);
            }
            if (direction.down == 1) {
                imageArrow = new ImageIcon("src/images/arrow/down.png");
                g2d.drawImage(imageArrow.getImage(),this.x, this.y +5, 5,20,Color.yellow, null);
            }
            if (direction.left == 1) {
                imageArrow = new ImageIcon("src/images/arrow/left.png");
                g2d.drawImage(imageArrow.getImage(),this.x+5, this.y, 20,5,Color.yellow, null);
            }
            if (direction.right == 1) {
                imageArrow = new ImageIcon("src/images/arrow/right.png");
                g2d.drawImage(imageArrow.getImage(),this.x+5, this.y + this.size_y -5, 20,5,Color.yellow, null);
            }
        }
        if (isEndNode) {
            g2d.setColor(Color.blue);
            g2d.setFont(new Font("Robo",Font.BOLD, 10));
            g2d.drawString("END",this.x+7, this.y + 20);
        }
        g2d.dispose();
    };
    public boolean isBelongTo(Facility facility){
        return ((this.x >= facility.x ) && (this.y >= facility.y) && (this.x <= facility.x + facility.size_x - 28) && (this.y <= facility.y + facility.size_y - 28));    }
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
    public void updateIsEndNode() {
        this.isEndNode = true;
    }

    public boolean isVisited() {return visited;}
    public void setVisited(boolean visited) {this.visited = visited;}
    public float getDist() {return dist;}
    public void setDist(float dist) {this.dist = dist;}
    public Node getPr() {return pr;}
    public void setPr(Node pr) {this.pr = pr;}
    public java.util.List<Edge> getList() {return list;}
    public void setList(java.util.List<Edge> list) {list = list;}
    public void addNeighbour(Edge edge) {
        list.add(edge);
    }
    @Override
    public int compareTo(Node other) {
        return java.lang.Float.compare(dist, other.getDist());
    }
}
