package com.zetcode.model;

import java.util.PriorityQueue;

public class FindPath {
    // Tim duong di ngan nhat tu SourceV
    public void ShortestPath(Node sourceV) {
        sourceV.setDist(0);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(sourceV);
        sourceV.setVisited(true);

        while (!priorityQueue.isEmpty()) {
            Node discardV = priorityQueue.poll();
            for (Edge edge : discardV.getList()) {
                Node v = edge.getTarget();
                if (!v.Visited()) {
                    v.setDist(discardV.getDist() + 1);
                    v.setPrevious(discardV);
                    v.setVisited(true);
                    priorityQueue.add(v);
                }
            }
        }
    }

    // Lay duong di ngan nhat toi moi diem
    public void getShortestPath(Node targetVertex) {
        for (Node vertex = targetVertex; vertex != null; vertex = vertex.getPrevious()) {
            vertex.updateIsLine(true);
        }
    }
}
