package com.zetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {
    private boolean visited;                    // Da duoc tham chua
    private String name;                        // Ten dinh
    private List<Edge> List;                    // Danh sach
    private int dist = Integer.MAX_VALUE;       // Dat khoang cach mac dinh la vo cung
    private Vertex previous;                    // Dinh nam truoc no
    private boolean isCover = false;            // Co bi phu khong
    private boolean inLine = false;             // Co nam tren duong di khong

    public Vertex(String name) {
        this.name = name;
        this.List = new ArrayList<>();
    }

    public List<Edge> getList() { return List; }
    public void setList(List<Edge> List) { this.List = List; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void addNeighbour(Edge edge) { this.List.add(edge); }
    public boolean Visited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }

    public Vertex getPrevious() { return previous; }
    public void setPrevious(Vertex previous) { this.previous = previous; }

    public int getDist() { return dist; }
    public void setDist(int dist) { this.dist = dist; }

    public void cover() { isCover = true; }
    public boolean getCover() { return isCover; }

    public void setInLine() { this.inLine = true; }
    public boolean getInLine() { return this.inLine; }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Vertex otherV) {
        return Double.compare(this.dist, otherV.getDist());
    }
}