package com.zetcode.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Graph {

    public ArrayList<Node> path= new ArrayList<>();

    public ArrayList<Node> getPath() {
        return path;
    }

    public void setPath(ArrayList<Node> path) {
        this.path = path;
    }

    public Graph() {

    }

    public void ShortestP(Node start) {
        start.setDist(0);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(start);
        start.setVisited(true);

        while (!priorityQueue.isEmpty()) {
            Node end = priorityQueue.poll();
            for (Edge edge : end.getList()) {
                Node v = edge.getEnd();

                if (!v.isVisited()) {
                    float newDistance = end.getDist() + edge.getWeight() + edge.getStart().getW();
                    if (newDistance < v.getDist()) {
                        priorityQueue.remove(v);
                        v.setDist((int) newDistance);
                        v.setPr(end);
                        priorityQueue.add(v);
                    }
                }
            }
            end.setVisited(true);
        }
    }

    public void getShortestP(Node end) {
        for (Node node = end; node != null; node = node.getPr()) {
            if (!node.ID.equals("Null")) path.add(node) ;
        }
        Collections.reverse(path);
        printGraph();
    }
    public void printGraph() {
        for(Node node: path) {
            System.out.print("[" + node.ID + "] ");
        }
        System.out.println();
    }

}