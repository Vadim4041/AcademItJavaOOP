package ru.academits.kozhevnikov.graph_main;

import ru.academits.kozhevnikov.graph.Graph;

import java.util.function.IntConsumer;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(11);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(6, 7);
        graph.addEdge(4, 7);

        graph.addEdge(8, 9);
        graph.addEdge(9, 10);
        graph.addEdge(10, 8);

        /*
        Tree markdown:
             0
           /    \
          1       2
         / \     / \
        3   4   5   6
              \    /
                 7

                 8
                | \
                |  9
                | /
                10
         */

        IntConsumer consumer = v -> System.out.print(v + " ");

        System.out.println("Breadth-first traversal:"); // 0 1 2 3 4 5 6 7 8 9 10
        graph.traverseBreadthFirst(consumer);
        System.out.println();

        System.out.println("Depth-first traversal (non-recursive):"); // 0 1 3 4 7 6 2 5 8 9 10
        graph.traverseDepthFirst(consumer);
        System.out.println();

        System.out.println("Depth-first traversal (recursive):"); // 0 1 3 4 7 6 2 5 8 9 10
        graph.traverseDepthFirstRecursively(consumer);
    }
}
