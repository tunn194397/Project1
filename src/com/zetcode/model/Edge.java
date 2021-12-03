package com.zetcode.model;

public class Edge {
    private int weight;
    private Node start;
    private Node target;

    public Edge(int weight, Node start, Node target) {
        this.weight = weight;   // trong so
        this.start = start;     // diem bat dau
        this.target = target;   // diem ket thuc
    }

    // Cac phuong thuc get va set trong so, diem bat dau va diem ket thuc cua canh

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
    public Node getStart() { return start; }
    public void setStart(Node start) { this.start = start; }
    public Node getTarget() { return target; }
    public void setTarget(Node target) { this.target = target; }
}
