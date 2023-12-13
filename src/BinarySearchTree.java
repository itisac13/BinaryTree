import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BinarySearchTree<T extends Comparable<T>> {
    private T data;
    private BinarySearchTree<T> leftNode;
    private BinarySearchTree<T> rightNode;
    private BinarySearchTree<T> parent;

    public void display() {
        /*
        System.out.println(this.data);
        if (this.leftNode != null)
            System.out.println("____" + this.leftNode.data);
        else
            System.out.println("____");

        if (this.rightNode != null)
            System.out.println("____" + this.rightNode.data);
        else
            System.out.println("____");

         */


        displayRecursive(this, 0);
    }
    // Getter and Setter for data
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Getter and Setter for leftNode
    public BinarySearchTree<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinarySearchTree<T> leftNode) {
        this.leftNode = leftNode;
    }

    // Getter and Setter for rightNode
    public BinarySearchTree<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinarySearchTree<T> rightNode) {
        this.rightNode = rightNode;
    }

    // Getter and Setter for parent
    public BinarySearchTree<T> getParent() {
        return parent;
    }

    public void setParent(BinarySearchTree<T> parent) {
        this.parent = parent;
    }
    private void displayRecursive(BinarySearchTree<T> node, int depth) {
        if (node != null) {
            // Print spaces for better visualization
            for (int i = 0; i < depth; i++) {
                System.out.print("  ");
            }

            // Print the data of the current node
            System.out.println(node.getData());

            // Recursively display the left and right subtrees
            displayRecursive(node.getLeftNode(), depth + 1);
            displayRecursive(node.getRightNode(), depth + 1);
        }
    }

    // Constructors, getters, setters...

    // Insert method for adding elements to the BST
    public void insert(T value) {
        if (data == null) {
            this.data = value;
        } else {
            if (value.compareTo(this.data) <= 0) {
                if (leftNode == null) {
                    leftNode = new BinarySearchTree<>();
                    leftNode.parent = this;
                }
                leftNode.insert(value);
            } else {
                if (rightNode == null) {
                    rightNode = new BinarySearchTree<>();
                    rightNode.parent = this;
                }
                rightNode.insert(value);
            }
        }
    }

    public BinarySearchTree<T> search(T value) {
        if (data == null || value.equals(data)) {
            return this;
        } else if (value.compareTo(data) < 0 && leftNode != null) {
            return leftNode.search(value);
        } else if (value.compareTo(data) > 0 && rightNode != null) {
            return rightNode.search(value);
        } else {
            return null; // Node not found
        }
    }
    // Delete method to remove a node with the specified data
    public void delete(T value) {
        BinarySearchTree<T> nodeToDelete = search(value);

        if (nodeToDelete != null) {
            deleteNode(nodeToDelete);
        }
    }

    private void deleteNode(BinarySearchTree<T> nodeToDelete) {
        if (nodeToDelete.getLeftNode() == null && nodeToDelete.getRightNode() == null) {
            // Case 1: Node to be deleted is a leaf node
            if (nodeToDelete.getParent() == null) {
                // Deleting the root node
                nodeToDelete.setData(null);
            } else if (nodeToDelete.getParent().getLeftNode() == nodeToDelete) {
                nodeToDelete.getParent().setLeftNode(null);
            } else {
                nodeToDelete.getParent().setRightNode(null);
            }
        } else if (nodeToDelete.getLeftNode() != null && nodeToDelete.getRightNode() != null) {
            // Case 2: Node to be deleted has two children
            BinarySearchTree<T> successor = findSuccessor(nodeToDelete.getRightNode());
            nodeToDelete.setData(successor.getData());
            deleteNode(successor);
        } else {
            // Case 3: Node to be deleted has one child
            BinarySearchTree<T> child = (nodeToDelete.getLeftNode() != null) ? nodeToDelete.getLeftNode() : nodeToDelete.getRightNode();

            if (nodeToDelete.getParent() == null) {
                // Deleting the root node
                setData(child.getData());
                setLeftNode(child.getLeftNode());
                setRightNode(child.getRightNode());
            } else if (nodeToDelete.getParent().getLeftNode() == nodeToDelete) {
                nodeToDelete.getParent().setLeftNode(child);
            } else {
                nodeToDelete.getParent().setRightNode(child);
            }

            if (child != null) {
                child.setParent(nodeToDelete.getParent());
            }
        }
    }

    private BinarySearchTree<T> findSuccessor(BinarySearchTree<T> node) {
        while (node.getLeftNode() != null) {
            node = node.getLeftNode();
        }
        return node;
    }



    // Other methods...
    public static void main(String[] args) {
        // Task 1: Randomly insert 10 non-repeated numbers from 0 to 9
        BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        List<Integer> numbersToAdd = generateUniqueNumbers(10);
        for (Integer num : numbersToAdd) {
            bst1.insert(num);
        }

        System.out.println("Task 1:");
        System.out.println("Final tree: ");
        bst1.display();
        System.out.println();

        // Task 2: Delete 3 numbers randomly picked from 0-9
        List<Integer> numbersToDelete = getRandomNumbers(numbersToAdd, 3);
        for (Integer num : numbersToDelete) {
            bst1.delete(num);
        }

        System.out.println("\nTask 2:");
        System.out.println("Final tree: ");
        bst1.display();
        System.out.println();

        // Task 3: Search 3 numbers from 0 to 9 randomly
        List<Integer> numbersToSearch = getRandomNumbers(numbersToAdd, 3);
        System.out.println("\nTask 3:");
        for (Integer num : numbersToSearch) {
            BinarySearchTree<Integer> resultNode = bst1.search(num);
            if (resultNode != null) {
                System.out.println("Search result for " + num + ": Node found - " + resultNode.getData());
            } else {
                System.out.println("Search result for " + num + ": Node not found.");
            }
        }
    }

    // Helper method to generate a list of unique numbers
    private static List<Integer> generateUniqueNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    // Helper method to get a random subset of numbers from a list
    private static List<Integer> getRandomNumbers(List<Integer> sourceList, int count) {
        List<Integer> randomNumbers = new ArrayList<>(sourceList);
        Collections.shuffle(randomNumbers);
        return randomNumbers.subList(0, count);
    }
}