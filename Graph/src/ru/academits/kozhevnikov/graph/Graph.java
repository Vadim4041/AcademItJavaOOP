package ru.academits.kozhevnikov.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] adjacentMatrix;
    private final int verticesCount;

    public Graph(int verticesCount) {
        if (verticesCount <= 0) {
            throw new IllegalArgumentException("Number of vertices must be positive.");
        }

        this.verticesCount = verticesCount;
        adjacentMatrix = new int[verticesCount][verticesCount];
    }

    public void addEdge(int startVertexIndex, int endVertexIndex) {
        if (startVertexIndex < 0 || startVertexIndex >= verticesCount || endVertexIndex < 0 || endVertexIndex >= verticesCount) {
            throw new IllegalArgumentException("Invalid vertex index.");
        }

        adjacentMatrix[startVertexIndex][endVertexIndex] = 1;
        adjacentMatrix[endVertexIndex][startVertexIndex] = 1;
    }

    public void breadthFirstTraversal(IntConsumer consumer) {
        boolean[] visited = new boolean[verticesCount];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < verticesCount; i++) {
            if (!visited[i]) {
                visited[i] = true;
                queue.add(i);

                while (!queue.isEmpty()) {
                    int vertex = queue.poll();
                    consumer.accept(vertex);

                    for (int j = 0; j < verticesCount; j++) {
                        if (adjacentMatrix[vertex][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }
                }
            }
        }
    }

    public void depthFirstTraversal(IntConsumer consumer) {
        boolean[] visited = new boolean[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            if (!visited[i]) {
                depthFirstRecursive(i, visited, consumer);
            }
        }
    }

    private void depthFirstRecursive(int vertex, boolean[] visited, IntConsumer consumer) {
        visited[vertex] = true;
        consumer.accept(vertex);

        for (int i = 0; i < verticesCount; i++) {
            if (adjacentMatrix[vertex][i] == 1 && !visited[i]) {
                depthFirstRecursive(i, visited, consumer);
            }
        }
    }
}
