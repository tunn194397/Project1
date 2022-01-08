package com.zetcode.model;

public class Edge {
    public int weight = 1;
    private Node start;
    private Node end;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
    }
    public Edge(int weight, Node start, Node end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

}