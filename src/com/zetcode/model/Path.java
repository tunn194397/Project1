package com.zetcode.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Path {
    private double distance;
    private int point;
    public Point[] route;
    public ArrayList<Point> routeZigZac = new ArrayList<>();
    public ArrayList<Point> route1 = new ArrayList<>();


    public Path(double distance, int point) {
        this.distance = distance;
        this.point = point;
        route = new Point[point];



    }

    public void drawRoute(Graphics g) {
        for (int i = 0; i < point - 1; i++) {
            g.drawLine(route[i].getX() , route[i].getY(), route[i+1].getX(), route[i+1].getY());
        }
    }



    public void changeRouteZigZac(Vector<Facility> facilities){
        int max ,min , bound;
        double a ,b;
        Random rand = new Random();
        for(int i = 0 ;i < point -1  ;i++){
            route1.add(route[i]);
            if(route[i+1].getX() - route[i].getX() ==0 ) a = 0;
            else{
                double x1 = route[i].getX();
                double x2 = route[i+1].getX();
                double y1 = route[i].getY();
                double y2 = route[i+1].getY();
                a = (y1 - y2 ) / (x1 - x2);
            }
            b = route[i].getY() - route[i].getX() * a ;
            if(route[i].getX() < route[i+1].getX()){
                min = route[i].getX();
                max = route[i+1].getX();
                bound = max - min;
            }
            else if(route[i].getX() > route[i+1].getX()){
                max = route[i].getX();
                min = route[i+1].getX();
                bound = max - min;
            }
            else{
                bound = 30;
                min = route[i].getX();
                max = route[i].getX();
            }

                    int randomX = rand.nextInt(bound) + min; //chon x bất kì
                    int randomY = (int)(rand.nextInt(60)+ a*randomX + b - 30);
                    Point newPoint1 = new Point(randomX ,randomY );
                    int xCheck1 = (randomX + route[i].getX())/2;
                    int yCheck1 = (int)(a*xCheck1 + b) ;
                    Point pointCheck1 = new Point(xCheck1,yCheck1);
                    newPoint1.checkCover(facilities );
                    if(newPoint1.isCover() == false && pointCheck1.isCover() == false  ){
                        route1.add(newPoint1);
                    }



            }
//        int xCheck2 = (route1.get(route1.size()-1).getX() + route1.get(route1.size()).getX())/2;
//        int yCheck2 = (route1.get(route1.size()-1).getY() + route1.get(route1.size()).getY())/2;
//        Point pointCheck2 = new Point(xCheck2,yCheck2);
//        if(pointCheck2.isCover() == true) route1.remove(route1.size()-1);
        route1.add(route[point-1]);
    }

    public void drawRoute1(Graphics g) {
        for (int i = 0; i < route1.size() - 1; i++) {
            g.drawLine(route1.get(i).getX(), route1.get(i).getY(), route1.get(i+1).getX(), route1.get(i+1).getY());
        }
    }

    public void printArray(){
        System.out.println(route.length);
        for (int i = 0; i < route.length; i++) {
            System.out.println("x: "+  route[i].getX() + " "+ " y: " + route[i].getY());
        }
    }

    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }

    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }
}
