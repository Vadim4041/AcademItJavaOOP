package ru.academits.kozhevnikov.tree_main;

import ru.academits.kozhevnikov.tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        // Insert nodes
        tree.insert(8);
        tree.insert(3);
        tree.insert(1);
        tree.insert(6);
        tree.insert(4);
        tree.insert(7);
        tree.insert(10);
        tree.insert(14);
        tree.insert(13);

        // Find a node in the tree
        System.out.println("Find node with value 6: " + tree.contains(6)); // true

        // Get the total number of nodes in the tree
        System.out.println("Total number of nodes: " + tree.getSize()); // 9

        // Traverse the tree breadth-first
        System.out.print("Breadth-first traversal: ");
        tree.traverseBreadthFirst(node -> System.out.print(node + " ")); // 8 3 10 1 6 14 4 7 13
        System.out.println();

        // Traverse the tree depth-first (non-recursive)
        System.out.print("Depth-first traversal (non-recursive): ");
        tree.traverseDepthFirst(node -> System.out.print(node + " ")); // 8 3 1 6 4 7 10 14 13
        System.out.println();

        // Traverse the tree depth-first (recursive)
        System.out.print("Depth-first traversal (recursive): ");
        tree.traverseDepthFirstRecursive(node -> System.out.print(node + " ")); // 8 3 1 6 4 7 10 14 13
        System.out.println();

        // Remove a node from the tree
        System.out.print("Breadth-first traversal after deleting node 6: ");
        tree.remove(6);
        tree.traverseBreadthFirst(node -> System.out.print(node + " ")); // 8 3 10 1 6 14 4 7 13
    }
}
