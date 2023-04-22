package ru.academits.kozhevnikov.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<T> {
    private Node<T> root;
    private Comparator<T> comparator;
    private int size;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int compare(T value1, T value2) {
        if (comparator == null) {
            if (value1 != null && value2 != null) {
                //noinspection unchecked
                return ((Comparable<T>) value1).compareTo(value2);
            }

            if (value1 == null && value2 == null) {
                return 0;
            }

            if (value1 == null) {
                return -1;
            }

            return 1;
        }

        return comparator.compare(value1, value2);
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
            if (compare(value, currentNode.getValue()) < 0) {
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


    public boolean contains(T value) {
        if (size == 0) {
            return false;
        }

        Node<T> currentNode = root;

        while (currentNode != null) {
            int comparisonResult = compare(value, currentNode.getValue());

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
        int comparisonResult = compare(value, currentNode.getValue());

        while (comparisonResult != 0) {
            parentNode = currentNode;

            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
                isLeftChild = true;
            } else {
                currentNode = currentNode.getRight();
                isLeftChild = false;
            }

            if (currentNode == null) {
                return false; // Node not found
            }

            comparisonResult = compare(value, currentNode.getValue());
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
            Node<T> leftmostNodeInRightSubtreeParent = currentNode;
            Node<T> leftmostNodeInRightSubtree = currentNode.getRight();

            while (leftmostNodeInRightSubtree.getLeft() != null) {
                leftmostNodeInRightSubtreeParent = leftmostNodeInRightSubtree;
                leftmostNodeInRightSubtree = leftmostNodeInRightSubtree.getLeft();
            }

            if (leftmostNodeInRightSubtree.getRight() == null) {
                // Check if right subtree has one node and delete it
                if (currentNode.getRight() == leftmostNodeInRightSubtree) {
                    leftmostNodeInRightSubtreeParent.setRight(null);
                } else {
                    leftmostNodeInRightSubtreeParent.setLeft(null);
                }
            } else {
                // If the leftmost node has the right child then link node's parent and successor
                leftmostNodeInRightSubtreeParent.setLeft(leftmostNodeInRightSubtree.getRight());
                leftmostNodeInRightSubtree.setRight(null);
            }

            if (currentNode == root) {
                root = leftmostNodeInRightSubtree; // Node is root, set root to leftmostNodeInRightSubtree
            } else if (isLeftChild) {
                parentNode.setLeft(leftmostNodeInRightSubtree); // Node is left child, set parent's left to leftmostNodeInRightSubtree
            } else {
                assert parentNode != null;
                parentNode.setRight(leftmostNodeInRightSubtree); // Node is right child, set parent's right to leftmostNodeInRightSubtree
            }

            leftmostNodeInRightSubtree.setLeft(currentNode.getLeft()); // Set leftmostNodeInRightSubtree's left to current's left
            leftmostNodeInRightSubtree.setRight(currentNode.getRight()); // Set leftmostNodeInRightSubtree's right to current's right
        }

        size--; // Decrease size of tree
        return true; // Node successfully removed
    }

    public void traverseBreadthFirst(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            consumer.accept(node.getValue());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void traverseDepthFirst(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Deque<Node<T>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            consumer.accept(currentNode.getValue());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    public void traverseDepthFirstRecursive(Consumer<T> consumer) {
        traverseDepthFirstRecursive(root, consumer);
    }

    private void traverseDepthFirstRecursive(Node<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getValue());

        traverseDepthFirstRecursive(node.getLeft(), consumer);
        traverseDepthFirstRecursive(node.getRight(), consumer);
    }
}