package com.zetcode.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FindPath {
    // Tim duong di ngan nhat
    public void ShortestPath(Vertex sourceV) {
        sourceV.setDist(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(sourceV);
        sourceV.setVisited(true);

        while (!priorityQueue.isEmpty()) {
            Vertex actualVertex = priorityQueue.poll();
            for (Edge edge : actualVertex.getList()) {
                Vertex v = edge.getTarget();

                if (!v.Visited()) {
                    int newDistance = actualVertex.getDist() + edge.getWeight();
                    if (newDistance < v.getDist()) {
                        priorityQueue.remove(v);
                        v.setDist(newDistance);
                        v.setPrevious(actualVertex);
                        priorityQueue.add(v);
                    }
                }
            }
            actualVertex.setVisited(true);
        }
    }

    // Lay duong di ngan nhat toi moi diem
    public List<Vertex> getShortestPath(Vertex targetVertex) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPrevious()) {
            path.add(vertex);
            vertex.setInLine();
        }
        Collections.reverse(path);
        return path;
    }
}
