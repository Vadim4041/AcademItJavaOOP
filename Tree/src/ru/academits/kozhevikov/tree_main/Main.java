package ru.academits.kozhevikov.tree_main;

import ru.academits.kozhevikov.tree.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert nodes
        bst.insert(8);
        bst.insert(3);
        bst.insert(1);
        bst.insert(6);
        bst.insert(4);
        bst.insert(7);
        bst.insert(10);
        bst.insert(14);
        bst.insert(13);

        // Find a node in the tree
        System.out.println("Find node with value 6: " + bst.findNode(6).getValue()); // 6

        // Get the total number of nodes in the tree
        System.out.println("Total number of nodes: " + bst.getNodeCount()); // 9

        // Traverse the tree in-order
        System.out.print("In-order traversal: ");
        bst.traverseInOrder(); // 1 3 4 6 7 8 10 13 14
        System.out.println();

        // Traverse the tree breadth-first (non-recursive)
        System.out.print("Breadth-first traversal (non-recursive): ");
        bst.traverseBreadthFirst(); // 8 3 10 1 6 14 4 7 13
        System.out.println();

        // Traverse the tree breadth-first (recursive)
        System.out.print("Breadth-first traversal (recursive): ");
        bst.traverseBreadthFirstRecursive(); // 8 3 10 1 6 14 4 7 13
        System.out.println();

        // Traverse the tree depth-first (non-recursive)
        System.out.print("Depth-first traversal (non-recursive): ");
        bst.traverseDepthFirst(); // 8 3 1 6 4 7 10 14 13
        System.out.println();

        // Traverse the tree depth-first (recursive)
        System.out.print("Depth-first traversal (recursive): ");
        bst.traverseDepthFirstRecursive(); // 8 3 1 6 4 7 10 14 13
        System.out.println();

        // Delete a node from the tree
        bst.delete(6);
        System.out.print("In-order traversal after deleting node 6: ");
        bst.traverseInOrder(); // 1 3 4 7 8 10 13 14
        System.out.println();
    }
}
