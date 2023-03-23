package ru.academits.kozhevnikov.graph_main;

import ru.academits.kozhevnikov.graph.DisconnectedGraph;

public class Main {
    public static void main(String[] args) {
        DisconnectedGraph graph = new DisconnectedGraph(7);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(6, 6);

        System.out.println("Breadth First Traversal:");
        graph.breadthFirstTraversal(0);

        System.out.println();

        System.out.println("Depth First Traversal:");
        graph.depthFirstTraversal(0);
    }
}
