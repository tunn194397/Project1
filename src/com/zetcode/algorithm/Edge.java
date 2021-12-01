package com.zetcode.algorithm;

public class Edge {
    private int weight;
    private Vertex start;
    private Vertex target;

    public Edge(int weight, Vertex start, Vertex target) {
        this.weight = weight;   // trong so
        this.start = start;     // diem bat dau
        this.target = target;   // diem ket thuc
    }

    // Cac phuong thuc get va set trong so, diem bat dau va diem ket thuc cua canh

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
    public Vertex getStart() { return start; }
    public void setStart(Vertex start) { this.start = start; }
    public Vertex getTarget() { return target; }
    public void setTarget(Vertex target) { this.target = target; }
}
