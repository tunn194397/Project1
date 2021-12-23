package com.zetcode.model;

import java.awt.*;

public class AGV extends Facility {

    public Node[][] nodeArray = new Node[40][20];
    public Node baseNode = new Node();
    public Node nextNode = new Node();
    public Direction direction = new Direction();
    public boolean canControl = false;
    public boolean delay = false;
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
            Node tmpUp = nodeArray[this.x / 30][this.y / 30 - 1];
            Node tmpDown = nodeArray[this.x / 30][this.y / 30 + 1];
            Node tmpLeft = nodeArray[this.x / 30 - 1][this.y / 30];
            Node tmpRight = nodeArray[this.x / 30 + 1][this.y / 30];
            Node tmpNode = (tmpUp.isLine && baseNode.direction.up == 1)? tmpUp:
                                (tmpDown.isLine && baseNode.direction.down == 1)?tmpDown:
                                        (tmpLeft.isLine && baseNode.direction.left == 1)?tmpLeft:
                                                (tmpRight.isLine && baseNode.direction.right == 1)? tmpRight: baseNode;

            delay = tmpNode.isBlocked;
            if (direction.up == 1) nextNode = tmpUp.isLine? tmpUp : tmpNode;
            else if (direction.down == 1) nextNode = tmpDown.isLine? tmpDown: tmpNode;
            else if (direction.left == 1) nextNode = tmpLeft.isLine? tmpLeft: tmpNode;
            else if (direction.right == 1) nextNode = tmpRight.isLine? tmpRight: tmpNode;
            else nextNode = tmpNode;
        }
    }

    public void updateBaseNode() {
        baseNode = nodeArray[this.x/30][this.y/30];
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
        Node oldNode = baseNode;
        findNextNode();
        updateDirection();
        updateBaseNode();
        setupMove();
        if (nextNode.isLine && !delay) {
            if (direction.up == 1) update(this.x, this.y - 10);
            if (direction.down == 1) update(this.x, this.y + 10);
            if (direction.left == 1) update(this.x - 10, this.y);
            if (direction.right == 1) update(this.x + 10, this.y);

            baseNode.updateIsBlocked(true);
            if (!baseNode.equals(oldNode)) nodeArray[oldNode.x/30][oldNode.y/30].updateIsBlocked(false);
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
}




