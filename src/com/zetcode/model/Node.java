package com.zetcode.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Node extends Rectangle implements Comparable<Node> {
    public int x;
    public int y;
    public final int size_x = 28;
    public final int size_y = 28;
    public Shape shape;
    public boolean isLine;
    private boolean visited;
    private Node previous;
    private boolean isCover;
    private int dist = Integer.MAX_VALUE;
    private List<Edge> List;

    public Node(int i, int j) {
        this.x = i;
        this.y = j;
        this.isCover = false;
        this.isLine = false;
        this.visited = false;
        this.List = new ArrayList<>();
    }
    public Node(){};
    public void updateNode(int i, int j) {
        this.x = i;
        this.y = j;
    }
    public void updateIsLine(boolean isLine) {
        this.isLine = isLine;
    }
    public boolean getIsLine() { return isLine; }
    public void updateIsCover(boolean isCover) { this.isCover = isCover; }
    public boolean getCover() { return isCover; }

    public int getDist() { return dist; }
    public void setDist(int dist) { this.dist = dist; }

    public List<Edge> getList() { return List; }

    public void addNeighbour(Edge edge) { this.List.add(edge); }
    public boolean Visited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }

    public Node getPrevious() { return previous; }
    public void setPrevious(Node previous) { this.previous = previous; }

    @Override
    public int compareTo(Node otherNode) {
        return Integer.compare(this.dist, otherNode.getDist());
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        if (isLine) {
            g2d.setColor(Color.black);
        }
        else g2d.setColor(Color.white);
        shape = new ZRectangle(this.x, this.y, this.size_x, this.size_y);
        g2d.fill(shape);
        g2d.dispose();
    };
    public boolean isBelongTo(Facility facility){
        if (this.x > facility.x && this.y > facility.y && this.x < facility.x + facility.size_x - size_x && this.y < facility.y + facility.size_y - size_y){
            return true;
        }
        else return false;
    }
    public Rectangle getBound() {
        return new Rectangle(this.x, this.y, this.size_x, this.size_y);
    }
}
