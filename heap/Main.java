import java.util.Scanner;

public class Main {

  static void printCommands() {
      System.out.println("""
        1. 'E' to Exit.
        2. 'P' to Print Heap.
        3. Enter any digit to insert into heap.
        4. 'D' to pop the heap.
        5. 'C' clear screen.
        6. 'A' again print commands.
        """);
    }

  public static void main(String[] args) {
    MaxHeap heap = new MaxHeap(256); 
    printCommands();
    Scanner scanner = new Scanner(System.in);
    while(true) {
      String input = scanner.nextLine().trim();
      // Check if input is a single character for specific commands
            if (input.equalsIgnoreCase("E")) {
                System.out.println("Exiting the program.");
                break;  // Exit the loop
            } else if (input.equalsIgnoreCase("P")) {
                heap.printHeapStructure();
                // Call your print heap method here
                // heap.printHeap();
            } else if (input.equalsIgnoreCase("D")) {
              try {
                Integer poppedValue = heap.pop();
                System.out.println("Popped value: " + poppedValue);
              } catch (Exception e) {
                //TODO: handle exception
                System.out.println(e.toString() + " ");
              } 
            } else if (input.equalsIgnoreCase("C")) {
              Utils utils = new Utils();
              utils.clearConsole();
              printCommands();
            } else if (input.equalsIgnoreCase("A")) {
              printCommands();
            }
            else {
                try {
                    // Try to parse input as an integer and insert into heap
                    int value = Integer.parseInt(input);
                    heap.insert(value);
                    // Call your insert method here
                    // heap.insert(value);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter 'E', 'P', 'D' 'C', 'A', or a number.");
                }
            }    }
    heap.printHeapStructure();
    // MinHeap minHeap = new MinHeap(8);
    // minHeap.insert(80);
    // minHeap.insert(10);
    // minHeap.insert(2);
    // minHeap.insert(1);
    // minHeap.insert(0);
    // minHeap.pop();
    // minHeap.pop();
    // minHeap.pop();
    // minHeap.printHeapStructure();
  }
}

class MaxHeap {
  private Integer[] data;
  MaxHeap(int size) {
    data = new Integer[size];
  }
  int length = 0;
  Utils utils = new Utils();
  void insert(int value) {
    // add value to the last index of the array
    int lastIndex = length - 1;
    try {
      data[lastIndex + 1] = value;
      length++;
    // check if heap is not full
    } catch (ArrayIndexOutOfBoundsException exception) {
      System.out.println("Heap is full.");
      System.out.println(exception);
      return;
    }

    // balance the tree
    int heapIndex = lastIndex + 1;
    
    while (heapIndex > 0) {
      int parentIndex = utils.parentIndex(heapIndex);
      int currentIndex = heapIndex;

      if (data[currentIndex] >= data[parentIndex]) {
      data = utils.swap(data, parentIndex, currentIndex);
      heapIndex = parentIndex;
      }
      else break;
    }
}

 Integer pop() {
    // replace root node with last node
    int lastIndex = length - 1;
    if (lastIndex < 0) {
      // System.out.println("Pop operation failed. Heap is already empty.");
      return null;
    }
    int poppedValue = data[0];
    data[0] = data[lastIndex];
    data[lastIndex] = null;
    length--;

    int parentIndex = 0;

    while (parentIndex < length) {
      // check left and right node
      int leftIndex = utils.leftIndex(parentIndex);
      int rightIndex = utils.rightIndex(parentIndex);
      
      if (leftIndex > length || rightIndex > length) break;

      int leftValue = data[leftIndex] != null ? data[leftIndex] : Integer.MIN_VALUE;
      int rightValue = data[rightIndex] != null ? data[rightIndex] : Integer.MIN_VALUE;
      int parentValue = data[parentIndex];

      if (parentValue < leftValue || parentValue < rightValue) {
        int temp = leftValue > rightValue ? leftIndex : rightIndex;
        data = utils.swap(data, parentIndex, temp);
        parentIndex = temp;
      }
      else break;
    }
    return poppedValue;
  }

// Prints the heap structure in levels
  void printHeapStructure() {
    int n = length;
    if (n == 0) {
        System.out.println("Heap is empty.");
        return;
    }

    int levels = (int) (Math.log(n) / Math.log(2)) + 1; // Calculate the number of levels in the heap
    int maxWidth = (int) Math.pow(2, levels - 1) * 2; // Calculate the width of the last level

    System.out.println("###############################################");
    for (int i = 0; i < levels; i++) {
        // Calculate the number of elements at the current level
        int numElements = (int) Math.pow(2, i);
        int spaceBetweenElements = maxWidth / numElements; // Space between elements
        int leadingSpaces = spaceBetweenElements / 2; // Leading spaces for the current level

        // Print leading spaces
        for (int j = 0; j < leadingSpaces; j++) {
            System.out.print(" ");
        }

        // Print elements at the current level
        for (int j = 0; j < numElements; j++) {
            int index = (int) Math.pow(2, i) - 1 + j; // Calculate the index of the element
            if (index < n && data[index] != null) { // Check if index is within bounds and not null
                System.out.print(data[index]); // Print current element
            }

            if (j < numElements - 1) { // If not the last element in the level
                for (int k = 0; k < spaceBetweenElements - 1; k++) {
                    System.out.print(" "); // Print space between elements
                }
            }
        }
        System.out.println(); // Move to the next line after printing the level
    }
    System.out.println("###############################################");
}
 

  void printHeapArray() {
    System.out.println();
    int i = 0;
    while (i < data.length && data[i] != null) {
      System.out.print(data[i] + " ");
      i++;
    }
    System.out.println();
  }
}

class MinHeap {
  private Integer[] data;
  MinHeap(int size) {
    data = new Integer[size];
  }
  int length = 0;
  Utils utils = new Utils();
  void insert(int value) {
    // if (length == size) {
    //   System.out.println("Heap is full.");
    //   return;
    // }
    try {
      data[length] = value;
      length++;
    } catch (ArrayIndexOutOfBoundsException exception) {
      System.out.println("Heap is full");
    }

    // balance the tree
    int currentIndex = length - 1;
    while (currentIndex > 0) {
      int parentIndex = utils.parentIndex(currentIndex);
      if (data[parentIndex] > data[currentIndex]) {
        data = utils.swap(data, parentIndex, currentIndex);
        currentIndex = parentIndex;
      }
      else break;
    }
  }

  Integer pop() {
    if (length < 1) {
      // throw new Exception("Heap is already empty. Pop operation failed.");
      return null;
    }
    int lastIndex = length - 1;
    int poppedValue = data[0];
    data[0] = data[lastIndex];
    data[lastIndex] = null;
    length--;

    int currentIndex = 0;
    while (currentIndex < length) {
      int parentIndex = currentIndex;
      int leftIndex = utils.leftIndex(parentIndex);
      int rightIndex = utils.rightIndex(parentIndex);
      
      if (leftIndex > length || rightIndex > length) break;

      int parentValue = data[parentIndex];
      int leftValue = data[leftIndex] != null ? data[leftIndex] : Integer.MAX_VALUE;
      int rightValue = data[rightIndex] != null ? data[rightIndex] : Integer.MAX_VALUE;

      if (parentValue > leftValue || parentValue > rightValue) {
        int tempIndex = leftValue < rightValue ? leftIndex : rightIndex;
        data = utils.swap(data, tempIndex, parentIndex);
        currentIndex = tempIndex;
      }
      else break;
    }
    return poppedValue;
  }

  void printHeapStructure() {
    int n = length;
    if (n == 0) {
        System.out.println("Heap is empty.");
        return;
    }

    int levels = (int) (Math.log(n) / Math.log(2)) + 1; // Calculate the number of levels in the heap
    int maxWidth = (int) Math.pow(2, levels - 1) * 2; // Calculate the width of the last level

    System.out.println("###############################################");
    for (int i = 0; i < levels; i++) {
        // Calculate the number of elements at the current level
        int numElements = (int) Math.pow(2, i);
        int spaceBetweenElements = maxWidth / numElements; // Space between elements
        int leadingSpaces = spaceBetweenElements / 2; // Leading spaces for the current level

        // Print leading spaces
        for (int j = 0; j < leadingSpaces; j++) {
            System.out.print("  ");
        }

        // Print elements at the current level
        for (int j = 0; j < numElements; j++) {
            int index = (int) Math.pow(2, i) - 1 + j; // Calculate the index of the element
            if (index < n && data[index] != null) { // Check if index is within bounds and not null
                System.out.print(data[index]); // Print current element
            }

            if (j < numElements - 1) { // If not the last element in the level
                for (int k = 0; k < spaceBetweenElements - 1; k++) {
                    System.out.print("  "); // Print space between elements
                }
            }
        }
        System.out.println(); // Move to the next line after printing the level
    }
    System.out.println("###############################################");
  }
}



class Utils {
  void clearConsole() {
    try {
          if (System.getProperty("os.name").contains("Windows")) {
              new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
          } else {
              new ProcessBuilder("clear").inheritIO().start().waitFor();
          }
      } catch (Exception e) {
          System.out.println("Error clearing console.");
      }
    }
    // Finds the index of the last non-null value in the array
    int lastIndexOfArray(Integer array[]) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) return i - 1;
        }
        return array.length - 1;
    }

    // Calculates the remaining length in the array after the first null value
    int remainLengthInArray(Integer array[]) {
        int len = array.length;
        int index = len;
        System.out.println("Len " + len);
        for (int i = 0; i < len; i++) {
            if (array[i] == null) {
                index = i; 
                break;
            }
        }
        System.out.println("index " + index);
        return len - index;
    }

    // Swaps two elements in the array
    Integer[] swap(Integer array[], int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }

    int parentIndex(int childIndex) {
      return (childIndex -1) / 2;
    }
    int leftIndex(int parentIndex) {
      return 2 * parentIndex + 1;
    }
    int rightIndex(int parentIndex) {
      return 2 * parentIndex + 2;
    }
}
