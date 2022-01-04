package com.zetcode.model;

import java.util.Vector;

public class Point {
    private int x, y;
    private boolean isCover = false;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void checkCover(Vector<Facility> facilities){
        for(Facility facility: facilities){
            if(((facility.getX()-45) < this.x && ((this.x < facility.getX() + facility.getSize_x() + 45)) &&
                    ((facility.getY()-45) <this.y) && ((this.y < facility.getY() + facility.getSize_y()+45)))){
                this.isCover = true ;
                break;
            }
        }

    }

    public boolean isCover() {return isCover;}

    public void setCover(boolean cover) {isCover = cover;}

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
}
