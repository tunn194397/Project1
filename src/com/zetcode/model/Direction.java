package com.zetcode.model;

public class Direction {
    public int up;
    public int down;
    public int right;
    public int left;
    public Direction(int x, int y, int z, int t) {
        this.up =x;
        this.down = y;
        this.right = z;
        this.left = t;
    }
    public Direction(){
        this.up = 0;
        this.down = 0;
        this.right = 0;
        this.left = 0;
    }
    public Direction(String s) {
        this.up = 0;
        this.down = 0;
        this.right = 0;
        this.left = 0;
        if (s.equals("up")) this.up = 1;
        if (s.equals("down")) this.down = 1;
        if (s.equals("left")) this.left = 1;
        if (s.equals("right")) this.right = 1;
    }
    public void updateUp(int i) {
        this.up = i;
    }
    public void updateDown(int i) {
        this.down = i;
    }
    public void updateLeft(int i) {
        this.left = i;
    }
    public void updateRight(int i) {
        this.right = i;
    }
    public void updateAll(int x, int y, int z, int t) {
        this.up =x;
        this.down = y;
        this.right = z;
        this.left = t;
    }
    public int numberOfDirection () {
        return up + down + left + right;
    }
    public boolean childDirection(Direction d) {
        if (up == 1 && d.up == 1) return true;
        if (down == 1 && d.down == 1) return true;
        if (left == 1 && d.left == 1) return true;
        if (right == 1 && d.right == 1) return true;
        else return false;
    }
}

