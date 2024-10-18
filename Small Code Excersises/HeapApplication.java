import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Student Name: Steve Fasoranti 
// Student Number: C00275756
//Date: 22/08/2024
// What does the code do?: The code reads from file 1-5 and then
// Outputs the numbers through those files with the following fuctionallities
// Insert
// Find Parent
// Find Child 
// Remove
public class HeapApplication {

    private int[] heap;
    private int size;
    private boolean isMinHeap;

    public HeapApplication(int capacity, boolean isMinHeap) {
        heap = new int[capacity + 1];
        size = 0;
        this.isMinHeap = isMinHeap;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftChild(int i) {
        return 2 * i;
    }

    private int rightChild(int i) {
        return 2 * i + 1;
    }

    // Checks if the heap is full
    public void insert(int x) {
        if (size == heap.length - 1) {
            throw new IllegalStateException("Heap is full");
        }

        size++;
        heap[size] = x;
        siftUp(size);
    }

    private void siftUp(int i) {
        while (i > 1 && compare(heap[i], heap[parent(i)])) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public int findParent(int i) {
        return heap[parent(i)];
    }

    public int[] findChildren(int i) {
        return new int[] { heap[leftChild(i)], heap[rightChild(i)] };
    }

    // Knows that all the numbers are deleted and prints out the following message
    public int deleteRoot() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int root = heap[1];
        heap[1] = heap[size];
        size--;
        siftDown(1);
        return root;
    }

    private void siftDown(int i) {
        int maxIndex = i;

        int left = leftChild(i);
        if (left <= size && compare(heap[left], heap[maxIndex])) {
            maxIndex = left;
        }

        int right = rightChild(i);
        if (right <= size && compare(heap[right], heap[maxIndex])) {
            maxIndex = right;
        }

        if (i != maxIndex) {
            swap(i, maxIndex);
            siftDown(maxIndex);
        }
    }

    private boolean compare(int child, int parent) {
        return isMinHeap ? child < parent : child > parent;
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public void displayHeap() {
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // Main / Debug System.out.print
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose file to load (1-5): ");
        int fileChoice = scanner.nextInt();

        // Construct the correct file path
        String folderPath = "data/";
        String fileName = "file" + fileChoice + ".txt";
        String filePath = folderPath + fileName;

        HeapApplication heapApp = new HeapApplication(100, true); // Min-heap

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            String[] numbers = line.split(",");

            for (String number : numbers) {
                heapApp.insert(Integer.parseInt(number.trim()));
            }

            heapApp.displayHeap();

            while (true) {
                System.out.println("The file reading heap application, made by Steve Fasoranti");
                System.out.println("Menu:");
                System.out.println("1. Insert");
                System.out.println("2. Find Parent");
                System.out.println("3. Find Children");
                System.out.println("4. Delete Root");
                System.out.println("5. Exit");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Enter number to insert:");
                        int num = scanner.nextInt();
                        heapApp.insert(num);
                        heapApp.displayHeap();
                        break;
                    case 2:
                        System.out.println("Enter position to find parent:");
                        int pos = scanner.nextInt();
                        System.out.println("Parent: " + heapApp.findParent(pos));
                        break;
                    case 3:
                        System.out.println("Enter position to find children:");
                        int posChild = scanner.nextInt();
                        int[] children = heapApp.findChildren(posChild);
                        System.out.println("Children: " + children[0] + ", " + children[1]);
                        break;
                    case 4:
                        System.out.println("Root deleted: " + heapApp.deleteRoot());
                        heapApp.displayHeap();
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
