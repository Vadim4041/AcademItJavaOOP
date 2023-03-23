package ru.academits.kozhevnikov.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DisconnectedGraph {
    private final int[][] adjMatrix;
    private final int numVertices;

    public DisconnectedGraph(int numVertices) {
        this.numVertices = numVertices;
        this.adjMatrix = new int[numVertices][numVertices];
    }

    public void addEdge(int src, int dest) {
        this.adjMatrix[src][dest] = 1;
        this.adjMatrix[dest][src] = 1;
    }

    public void breadthFirstTraversal(int start) {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    public void depthFirstTraversal(int start) {
        boolean[] visited = new boolean[numVertices];
        Stack<Integer> stack = new Stack<>();

        visited[start] = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            System.out.print(vertex + " ");

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    stack.push(i);
                }
            }
        }
    }
}
