import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear una lista enlazada y un árbol binario de búsqueda
        SinglyLinkedList linkedList = new SinglyLinkedList();
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        // Generar 10K elementos aleatorios y añadirlos a la lista y al árbol
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int value = random.nextInt(10000);
            linkedList.add(value);
            binarySearchTree.insert(value);
        }

        // Solicitar el elemento a buscar
        Scanner scanner = new Scanner(System.in);
        int elementToFind;
        do {
            System.out.print("Ingrese el elemento a buscar (entre 0 y 9999): ");
            elementToFind = scanner.nextInt();
        } while (elementToFind < 0 || elementToFind > 9999);

        scanner.close();

        System.out.println("Elemento a buscar: " + elementToFind);

        // Medir el tiempo de búsqueda en la lista enlazada
        long startTime = System.nanoTime();
        boolean foundInLinkedList = linkedList.contains(elementToFind);
        long endTime = System.nanoTime();
        long durationInLinkedList = endTime - startTime;
        System.out.println("Tiempo de búsqueda en lista enlazada: " + durationInLinkedList + " nanosegundos");

        // Medir el tiempo de búsqueda en el árbol binario de búsqueda
        startTime = System.nanoTime();
        boolean foundInBinarySearchTree = binarySearchTree.search(elementToFind);
        endTime = System.nanoTime();
        long durationInBinarySearchTree = endTime - startTime;
        System.out.println("Tiempo de búsqueda en árbol binario de búsqueda: " + durationInBinarySearchTree + " nanosegundos");
    }
}

// Las clases SinglyLinkedList y BinarySearchTree permanecen igual...


//Lista enlazada
class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}

class SinglyLinkedList {
    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public boolean contains(int element) {
        Node current = this.head;
        while (current != null) {
            if (current.value == element) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean add(int element) {
        Node newNode = new Node(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
        return true;
    }
}

//Arbol de busqueda binario
class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int item) {
        key = item;
        left = right = null;
    }
}

class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    private TreeNode insertRecursive(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.key) {
            root.left = insertRecursive(root.left, key);
        } else if (key > root.key) {
            root.right = insertRecursive(root.right, key);
        }

        return root;
    }

    public boolean search(int key) {
        return searchRecursive(root, key);
    }

    private boolean searchRecursive(TreeNode root, int key) {
        if (root == null || root.key == key)
            return root != null;

        if (root.key < key)
            return searchRecursive(root.right, key);

        return searchRecursive(root.left, key);
    }
}
