package com.zetcode.model;

import java.awt.*;

public class AGV extends Facility {

    public Node[][] nodeArray = new Node[40][20];
    public Node baseNode = new Node();
    public Node nextNode = new Node();
    public Direction direction = new Direction();
    public boolean canControl = false;
    public int priority = 50;
    public int nextNodePriority = 0;

    public AGV() {
        size_x = 30;
        size_y = 15;
    }
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
    public AGV(int i, int j, Node[][] nodeArray) {
        super(i, j);
        size_x = 30;
        size_y = 15;
        imagePath = "src/resources/images/";
        moveAbility = true;
        priority = (int) (Math.random() * 100 + 1);
        name = "AGV";
        ID = "AGV" + Integer.toString(i) + Integer.toString(j);
        color = Color.red;
        this.nodeArray = nodeArray;
        baseNode = nodeArray[x/30][y/30];
        findNextNode();
    }

    public boolean inLine() {
        Node tmpNode = nodeArray[this.x/30][this.y/30];
        return tmpNode.isLine;
    }

    public void findNextNode() {
        if (inLine()) {
            Node tmpUp = (this.y /30 - 1 >= 0)?nodeArray[this.x / 30][this.y / 30 - 1]: new Node();
            Node tmpDown = (this.y / 30 + 1 < 20)? nodeArray[this.x / 30][this.y / 30 + 1]: new Node();
            Node tmpLeft = (this.x/30 - 1 >= 0)? nodeArray[this.x / 30 - 1][this.y / 30]: new Node();
            Node tmpRight = (this.x/30 + 1 < 40) ? nodeArray[this.x / 30 + 1][this.y / 30]: new Node();
            Node tmpNode = (tmpUp.isLine && baseNode.direction.up == 1)? tmpUp:
                                (tmpDown.isLine && baseNode.direction.down == 1)?tmpDown:
                                        (tmpLeft.isLine && baseNode.direction.left == 1)?tmpLeft:
                                                (tmpRight.isLine && baseNode.direction.right == 1)? tmpRight: baseNode;

            if (direction.up == 1) nextNode = tmpUp.isLine? tmpUp : tmpNode;
                else if (direction.down == 1) nextNode = tmpDown.isLine? tmpDown: tmpNode;
                else if (direction.left == 1) nextNode = tmpLeft.isLine? tmpLeft: tmpNode;
                else if (direction.right == 1) nextNode = tmpRight.isLine? tmpRight: tmpNode;
                else nextNode = tmpNode;

        }
    }

    public void updateBaseNode() {
        baseNode = nodeArray[x/30][y/30];
    }
    public void updateDirection() {
        if (baseNode.direction.numberOfDirection() == 1 && !baseNode.equals(nextNode)) direction = baseNode.direction;
        else if (nextNode.x > baseNode.x) {
            direction = new Direction("right");
        } else if (nextNode.x < baseNode.x) {
            direction = new Direction("left");
        } else if (nextNode.y > baseNode.y) {
            direction = new Direction("down");
        } else if (nextNode.y < baseNode.y) {
            direction = new Direction("up");
        } else direction = new Direction();
    }

    public void move() {
        if (isAllIn(baseNode)) {
            nodeArray[baseNode.coordinate_x][baseNode.coordinate_y].freeNode(); // danh dau node da di qua la khong con agv nay nua
            updateBaseNode();   // update basenode la node dang giu tron AGV truoc
            findNextNode();        // update nextnode la node tiep theo
            updateDirection();  //update direction dua theo base node va next node
            setupMove();  // chinh huong agv
            nodeArray[baseNode.coordinate_x][baseNode.coordinate_y].setAgvPriority(this);
            nodeArray[baseNode.coordinate_x][baseNode.coordinate_y].isBlocked = true;
            if (nodeArray[nextNode.coordinate_x][nextNode.coordinate_y].agvPriority == 0)
                nodeArray[nextNode.coordinate_x][nextNode.coordinate_y].setAgvPriority(this);
            else if (nodeArray[nextNode.coordinate_x][nextNode.coordinate_y].agvPriority < this.priority && !nodeArray[nextNode.coordinate_x][nextNode.coordinate_y].isBlocked) {
                nodeArray[nextNode.coordinate_x][nextNode.coordinate_y].setAgvPriority(this);
            }
        }
        else {
            nodeArray[nextNode.coordinate_x][nextNode.coordinate_y].isBlocked = true;
        }

        if (nextNode.isLine) {
            System.out.println(nextNode.agvPriority);
            if  ( (nextNode.agvPriority == 0) || nextNode.agvPriority == this.priority) {
                if (direction.up == 1) update(this.x, this.y - 3);
                if (direction.down == 1) update(this.x, this.y + 3);
                if (direction.left == 1) update(this.x - 3, this.y);
                if (direction.right == 1) update(this.x + 3, this.y);
                nodeArray[nextNode.coordinate_x][nextNode.coordinate_y].setU(0);
            }
            else {
                if (nodeArray[nextNode.coordinate_x][nextNode.coordinate_y].agvPriority != nextNodePriority) {
                    nodeArray[baseNode.coordinate_x][baseNode.coordinate_y].updateDelayTime();
                }
                nextNodePriority = nodeArray[nextNode.coordinate_x][nextNode.coordinate_y].agvPriority;
            }
        }
    }
    public void setControl (boolean control) {
        canControl = control;
    }
    public void setupMove() {
        if (direction.up == 1 || direction.down == 1) {
            this.size_x = 15;
            this.size_y = 30;
        }
        else {
            if (direction.left == 1 || direction.right == 1) {
                this.size_x = 30;
                this.size_y = 15;
            }
        }
    }

    public boolean isAllIn(Node node) {
        int i1 = this.x/30;
        int j1 = this.y/30;
        int i2 = (this.x + this.size_x - 1)/30;
        int j2 = (this.y + this.size_y - 1)/30;
        return i1 == i2 && j1 == j2;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(this.color);
        shape = new ZRectangle(this.x, this.y, this.size_x, this.size_y);
        g2d.fill(shape);
        g2d.setColor(Color.black);
        g2d.draw(shape);
        g2d.dispose();
        g.setColor(Color.black);
        g.drawString(String.valueOf(priority), this.x , this.y+10);
    }
}




