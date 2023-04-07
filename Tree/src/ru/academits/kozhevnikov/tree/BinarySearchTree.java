package ru.academits.kozhevnikov.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private Comparator<T> comparator;
    private int size;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int compare(T value1, T value2) {
        if (comparator != null) {
            return comparator.compare(value1, value2);
        }

        return value1.compareTo(value2);
    }

    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
            size++;

            return;
        }

        Node<T> currentNode = root;

        while (true) {
            // value is less than the current node
            if (compare(value, currentNode.getData()) < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(new Node<>(value));
                    size++;

                    return;
                }

                currentNode = currentNode.getLeft();
                continue;
            }

            // value is not less than the current node
            if (currentNode.getRight() == null) {
                currentNode.setRight(new Node<>(value));
                size++;

                return;
            }

            currentNode = currentNode.getRight();
        }
    }


    public boolean contains(T data) {
        if (size == 0) {
            return false;
        }

        Node<T> currentNode = root;

        while (currentNode != null) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    public int getSize() {
        return size;
    }

    public boolean remove(T value) {
        if (root == null) {
            return false; // Tree is empty, node not found
        }

        Node<T> currentNode = root;
        Node<T> parentNode = null;
        boolean isLeftChild = false;

        // Search for the node to be removed
        while (currentNode != null && !currentNode.getData().equals(value)) {
            parentNode = currentNode;

            if (compare(value, currentNode.getData()) < 0) {
                currentNode = currentNode.getLeft();
                isLeftChild = true;
            } else {
                currentNode = currentNode.getRight();
                isLeftChild = false;
            }
        }

        if (currentNode == null) {
            return false; // Node not found
        }

        // Case 1: Node has no children
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (currentNode == root) {
                root = null; // Node is root, set root to null
            } else if (isLeftChild) {
                parentNode.setLeft(null); // Node is left child, set parent's left to null
            } else {
                assert parentNode != null;
                parentNode.setRight(null); // Node is right child, set parent's right to null
            }
        }

        // Case 2: Node has one child
        else if (currentNode.getLeft() == null) {
            if (currentNode == root) {
                root = currentNode.getRight(); // Node is root, set root to right child
            } else if (isLeftChild) {
                parentNode.setLeft(currentNode.getRight()); // Node is left child, set parent's left to right child
            } else {
                assert parentNode != null;
                parentNode.setRight(currentNode.getRight()); // Node is right child, set parent's right to right child
            }

        } else if (currentNode.getRight() == null) {
            if (currentNode == root) {
                root = currentNode.getLeft(); // Node is root, set root to left child
            } else if (isLeftChild) {
                parentNode.setLeft(currentNode.getLeft()); // Node is left child, set parent's left to left child
            } else {
                assert parentNode != null;
                parentNode.setRight(currentNode.getLeft()); // Node is right child, set parent's right to left child
            }
        }

        // Case 3: Node has two children
        else {
            Node<T> successor = getSuccessor(currentNode);

            if (currentNode == root) {
                root = successor; // Node is root, set root to successor
            } else if (isLeftChild) {
                parentNode.setLeft(successor); // Node is left child, set parent's left to successor
            } else {
                assert parentNode != null;
                parentNode.setRight(successor); // Node is right child, set parent's right to successor
            }

            successor.setLeft(currentNode.getLeft()); // Set successor's left to current's left
        }

        size--; // Decrease size of tree
        return true; // Node successfully removed
    }

    private Node<T> getSuccessor(Node<T> node) {
        if (node == null) {
            return null;
        }

        // If the node has a right subtree, the successor will be the leftmost
        // node in the right subtree.
        if (node.getRight() != null) {
            Node<T> currentNode = node.getRight();

            while (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            }

            return currentNode;
        }

        // If the node does not have a right subtree, the successor will be
        // the first ancestor that is greater than the node's value.
        Node<T> successor = null;
        Node<T> ancestor = root;

        while (ancestor != null && !ancestor.equals(node)) {
            int comparisonResult = compare(node.getData(), ancestor.getData());

            if (comparisonResult < 0) {
                successor = ancestor;
                ancestor = ancestor.getLeft();
            } else {
                ancestor = ancestor.getRight();
            }
        }

        return successor;
    }

    public void traverseBreadthFirst(Consumer<T> action) {
        if (root == null) {
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            action.accept(node.getData());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void traverseDepthFirst(Consumer<T> action) {
        if (root == null) {
            return;
        }

        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            action.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    public void traverseDepthFirstRecursive(Consumer<T> action) {
        traverseDepthFirstRecursive(root, action);
    }

    private void traverseDepthFirstRecursive(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        action.accept(node.getData());

        traverseDepthFirstRecursive(node.getLeft(), action);
        traverseDepthFirstRecursive(node.getRight(), action);
    }



/*

    public void traverseDepthFirstRecursive() {
        // implementation to be added
    }

    public void traverseDepthFirstNonRecursive() {
        // implementation to be added
    }

    private void insertRecursive(Node<T> current, T data) {
        // implementation to be added
    }

    private Node<T> findNodeRecursive(Node<T> current, T data) {
        // implementation to be added
    }

    private Node<T> findMinimumNode(Node<T> node) {
        // implementation to be added
    }

    private Node<T> removeNodeRecursive(Node<T> current, T data) {
        // implementation to be added
    }

    private void traverseBreadthFirst(Node<T> root) {
        // implementation to be added
    }

    private void traverseDepthFirstRecursive(Node<T> current) {
        // implementation to be added
    }

    private void traverseInOrderNonRecursive(Node<T> root) {
        // implementation to be added
    }

 */
}