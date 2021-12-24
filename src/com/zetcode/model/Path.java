package com.zetcode.model;

import java.awt.*;

public class Path {
    private double distance;
    private int point;
    public Point[] route;

    public Path(double distance, int point) {
        this.distance = distance;
        this.point = point;
        route = new Point[point];
    }

    public void drawRoute(Graphics g) {
        for (int i = 0; i < point - 1; i++) {
            g.drawLine(route[i].getX(), route[i].getY(), route[i+1].getX(), route[i+1].getY());
        }
    }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }
}
