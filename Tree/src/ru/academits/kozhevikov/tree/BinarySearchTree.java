package ru.academits.kozhevikov.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value <= current.getValue()) {
            current.setLeft(insertRecursive(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(insertRecursive(current.getRight(), value));
        } else {
            return current;
        }

        return current;
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.getValue()) {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            if (current.getRight() == null) {
                return current.getLeft();
            }

            if (current.getLeft() == null) {
                return current.getRight();
            }

            int smallestValue = findSmallestValue(current.getRight());
            current.setValue(smallestValue);
            current.setRight(deleteRecursive(current.getRight(), smallestValue));
            return current;
        }

        if (value < current.getValue()) {
            current.setLeft(deleteRecursive(current.getLeft(), value));
            return current;
        }

        current.setRight(deleteRecursive(current.getRight(), value));
        return current;
    }

    private int findSmallestValue(Node root) {
        return root.getLeft() == null ? root.getValue() : findSmallestValue(root.getLeft());
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node node) {
        if (node == null) {
            return;
        }

        traverseInOrder(node.getLeft());
        System.out.print(node.getValue() + " ");
        traverseInOrder(node.getRight());
    }

    public void traverseBreadthFirst() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.getValue() + " ");

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void traverseBreadthFirstRecursive() {
        int height = getHeight(root);

        for (int i = 1; i <= height; i++) {
            traverseBreadthFirstRecursive(root, i);
        }
    }

    private void traverseBreadthFirstRecursive(Node node, int level) {
        if (node == null) {
            return;
        }

        if (level == 1) {
            System.out.print(node.getValue() + " ");
        } else if (level > 1) {
            traverseBreadthFirstRecursive(node.getLeft(), level - 1);
            traverseBreadthFirstRecursive(node.getRight(), level - 1);
        }
    }

    public void traverseDepthFirst() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.getValue() + " ");

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    public void traverseDepthFirstRecursive() {
        traverseDepthFirstRecursive(root);
    }

    private void traverseDepthFirstRecursive(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getValue() + " ");
        traverseDepthFirstRecursive(node.getLeft());
        traverseDepthFirstRecursive(node.getRight());
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public Node findNode(int value) {
        return findNode(root, value);
    }

    private Node findNode(Node node, int value) {
        if (node == null || node.getValue() == value) {
            return node;
        }

        if (value < node.getValue()) {
            return findNode(node.getLeft(), value);
        } else {
            return findNode(node.getRight(), value);
        }
    }

    public int getNodeCount() {
        return getNodeCount(root);
    }

    private int getNodeCount(Node node) {
        if (node == null) {
            return 0;
        }

        return getNodeCount(node.getLeft()) + getNodeCount(node.getRight()) + 1;
    }
}
