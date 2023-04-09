package ru.academits.kozhevnikov.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.IntConsumer;

public class Graph {
    private final int[][] adjacencyMatrix;
    private final int verticesCount;

    public Graph(int verticesCount) {
        if (verticesCount <= 0) {
            throw new IllegalArgumentException(String.format("Number of vertices %d: Number of vertices must grater than zero.", verticesCount));
        }

        this.verticesCount = verticesCount;
        adjacencyMatrix = new int[verticesCount][verticesCount];
    }

    public void addEdge(int startVertexIndex, int endVertexIndex) {
        if (startVertexIndex < 0 || startVertexIndex >= verticesCount) {
            throw new IndexOutOfBoundsException(String.format("Start vertex index: %d. " +
                    "Start vertex index must be positive and less then the total number of vertices: %d", startVertexIndex, verticesCount));
        }

        if (endVertexIndex < 0 || endVertexIndex >= verticesCount) {
            throw new IndexOutOfBoundsException(String.format("End vertex index: %d. " +
                    "End vertex index must be positive and less then the total number of vertices: %d", endVertexIndex, verticesCount));
        }

        adjacencyMatrix[startVertexIndex][endVertexIndex] = 1;
        adjacencyMatrix[endVertexIndex][startVertexIndex] = 1;
    }

    public void traverseBreadthFirst(IntConsumer consumer) {
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
                        if (adjacencyMatrix[vertex][j] == 1 && !visited[j]) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }
                }
            }
        }
    }

    public void traverseDepthFirstRecursively(IntConsumer consumer) {
        boolean[] visited = new boolean[verticesCount];

        for (int i = 0; i < verticesCount; i++) {
            if (!visited[i]) {
                traverseDepthFirstRecursively(i, visited, consumer);
            }
        }
    }

    private void traverseDepthFirstRecursively(int vertex, boolean[] visited, IntConsumer consumer) {
        visited[vertex] = true;
        consumer.accept(vertex);

        for (int i = 0; i < verticesCount; i++) {
            if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                traverseDepthFirstRecursively(i, visited, consumer);
            }
        }
    }

    public void traverseDepthFirst(IntConsumer consumer) {
        boolean[] visited = new boolean[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            if (!visited[i]) {
                traverseDepthFirst(i, visited, consumer);
            }
        }
    }

    private void traverseDepthFirst(int startVertex, boolean[] visited, IntConsumer consumer) {
        visited[startVertex] = true;
        consumer.accept(startVertex);
        int[] stack = new int[verticesCount];
        int top = 0;
        stack[top] = startVertex;

        while (top >= 0) {
            int vertex = stack[top];
            top--;

            for (int i = verticesCount - 1; i >= 0; i--) {
                if (adjacencyMatrix[vertex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    consumer.accept(i);
                    top++;
                    stack[top] = i;
                }
            }
        }
    }
}
