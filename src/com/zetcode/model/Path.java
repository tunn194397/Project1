package com.zetcode.model;

import java.awt.*;

public class Path {
    private double distance;
    private int point;
    public Point[] route;
    float delta = 0;
    int deltaX, deltaY, current = 0;
    boolean isInitial = true, isReverse = false;

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

    public void doMove(Agent a) {
        if(isInitial) {
            if(!isReverse) {
                a.x = route[current].getX();
                a.y = route[current].getY();
                if (current == point - 1) {
                    isReverse = true;
                    deltaX = deltaY = 0;
                    return;
                }
                deltaX = route[current + 1].getX() - route[current].getX();
                deltaY = route[current + 1].getY() - route[current].getY();
                current++;
            } else {
                a.x = route[current].getX();
                a.y = route[current].getY();
                if (current == 0) {
                    isReverse = false;
                    deltaX = deltaY = 0;
                    return;
                }
                deltaX = route[current - 1].getX() - route[current].getX();
                deltaY = route[current - 1].getY() - route[current].getY();
                current--;
            }
            isInitial = false;
        }
        int x = Math.abs(deltaX);
        int y = Math.abs(deltaY);
        if (x > y) {
            delta = (float) deltaY / deltaX;
            if (deltaX > 5) {
                int z = (int) (5 * delta);
                a.x += 5;
                a.y += z;
                deltaX -= 5;
                deltaY -= z;
            } else if (deltaX < -5) {
                int z = (int) (5 * delta);
                a.x -= 5;
                a.y -= z;
                deltaX += 5;
                deltaY += z;
            } else isInitial = true;
        } else {
            delta = (float) deltaX / deltaY;
            if (deltaY > 5) {
                int z = (int) (5 * delta);
                a.y += 5;
                a.x += z;
                deltaY -= 5;
                deltaX -= z;
            } else if (deltaY < -5) {
                int z = (int) (5 * delta);
                a.y -= 5;
                a.x -= z;
                deltaY += 5;
                deltaX += z;
            } else isInitial = true;
        }
    }
}
