package ru.academits.kozhevnikov.graph_main;

import ru.academits.kozhevnikov.graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(9);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(6, 6);

        System.out.println("Breadth-first traversal:");
        graph.breadthFirstTraversal(v -> System.out.print(v + " "));
        System.out.println();
        System.out.println("Depth-first traversal:");
        graph.depthFirstTraversal(v -> System.out.print(v + " "));
    }
}
