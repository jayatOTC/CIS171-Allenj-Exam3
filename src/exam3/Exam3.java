/*
* Program:         Exam3
* Developer:       Jay Allen
* Date:            04/11/2019
* Purpose:         A) initialize elementCount to 50,000 100,000 150,000
*                       200,000 250,000 and 300,000 random elements.
*                  B) create an array with "ElementCount" elements.
*                  C) copy the originalArray to sortArray
*                  D) sort the SortArray using 
*                       Selection sort recording the start time and end time.
*                       subtract the start time from the end time to get
*                       the elapsed time.
*                  E) place the elapsed time on the report line.
*                  F) Add 50,000 elements to elementCount
*                  G) Perform B through F replacing Selection Sort with
*                       Radix Sort
*                  H) Perform B through F replacing Radix Sort with
*                       Bubble Sort
*                  I) Perform B through F replacing Bubble Sort with
*                       Merge Sort
*                  J) Perform B through F replacing Merge Sort with
*                       Quick Sort
*                  K) Perform b through F replacing Quick Sort with
*                       Heap Sort
*/
package exam3;

// import java.util.Arrays;
import java.util.*;

/**
 *
 * @author Jay Allen
 */
public class Exam3 {
    
    public static void buildArray() {
    
}
    public static void selectionSort(int[] list)
    {  
        for (int i = 0; i < list.length - 1; i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < list.length; j++){  
                if (list[j] < list[index]){  
                    index = j;//searching for lowest index  
                }  // end IF
            }  // end for
            int smallerNumber = list[index];   
            list[index] = list[i];  
            list[i] = smallerNumber;  
        }   // end for
    } // end SelectionSort
    
 /** Sort the list of items */
    public static void radixSort(int [] list, int numDigits)
    {        
            // creates buckets to use for sorting
        java.util.ArrayList<Integer>[] buckets = new java.util.ArrayList[10];
        
            // makes each bucket an array list
        for (int i = 0; i < buckets.length; i++){
            buckets[i] = new java.util.ArrayList<Integer>();           
        } // end of for loop
        
            // begins searching by position
        for ( int pos = 0; pos <= numDigits; pos++) {
            
                // clear buckets        // needed for later passes
            for(int i = 0; i < buckets.length; i++){
                buckets[i].clear();
            } // end of clear for loop
            
                // distribute elements from list to buckets
            for (int i = 0; i < list.length; i++){
                int key = getKey(list[i], pos);
                buckets[key].add(list[i]);
            } // end of distribute for loop
            
                // now move from buckets back to list
            int k = 0;      // k is index for list
            for (int i = 0; i < buckets.length; i++){
                for (int j = 0; j < buckets[i].size(); j++){
                    list[k++] = buckets[i].get(j);
                } // end of inner for loop
            } // end of outer for loop
            
        } // end of for loop 
        
    } // end of radixSort

     public static int getKey(int number, int position){
         int res = 1;
         for (int i = 0; i < position; i++)
             res *= 10;
         return (number / res) % 10;
     } // end of getKey
    
    public static void bubbleSort(int[] list) 
    {
        boolean needNextPass = true;
        
        for (int k = 1; k < list.length && needNextPass; k++)
        {
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++){
                if (list[i] > list[i + 1]){
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    
                    needNextPass = true;
                } // end of if
            } // end of inner for loop
        } // end of for loop
        
    } // end of bubbleSort
    
    //  Merge sort
    
  /** The method for sorting the numbers */
  public static void mergeSort(int[] list) 
  {
    if (list.length > 1) 
    {
      // Merge sort the first half
      int[] firstHalf = new int[list.length / 2];
      System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
      mergeSort(firstHalf);

      // Merge sort the second half
      int secondHalfLength = list.length - list.length / 2;
      int[] secondHalf = new int[secondHalfLength];
      System.arraycopy(list, list.length / 2,
        secondHalf, 0, secondHalfLength);
      mergeSort(secondHalf);

      // Merge firstHalf with secondHalf into list
      merge(firstHalf, secondHalf, list);
    }
  } // end mergeSort
  
  /** Merge two sorted lists */
  public static void merge(int[] list1, int[] list2, int[] temp) {
    int current1 = 0; // Current index in list1
    int current2 = 0; // Current index in list2
    int current3 = 0; // Current index in temp

    while (current1 < list1.length && current2 < list2.length) 
    {
      if (list1[current1] < list2[current2])
        temp[current3++] = list1[current1++];
      else
        temp[current3++] = list2[current2++];
    }   // end while

    while (current1 < list1.length)
      temp[current3++] = list1[current1++];

    while (current2 < list2.length)
      temp[current3++] = list2[current2++];
  } // end merge
  
    public static void quickSort(int[] list) {
       quickSort(list, 0, list.length - 1);
     }

    private static void quickSort(int[] list, int first, int last) {
      if (last > first) {
        int pivotIndex = partition(list, first, last);
        quickSort(list, first, pivotIndex - 1);
        quickSort(list, pivotIndex + 1, last);
      }
    }

    /** Partition the array list[first..last] */
    private static int partition(int[] list, int first, int last) {
      int pivot = list[first]; // Choose the first element as the pivot
      int low = first + 1; // Index for forward search
      int high = last; // Index for backward search

      while (high > low) 
      {
        // Search forward from left
        while (low <= high && list[low] <= pivot)
          low++;

        // Search backward from right
        while (low <= high && list[high] > pivot)
          high--;

        // Swap two elements in the list
        if (high > low) {
          int temp = list[high];
          list[high] = list[low];
          list[low] = temp;
        }
      }

      while (high > first && list[high] >= pivot)
        high--;    
        // Swap pivot with list[high]
        if (pivot > list[high]) {
          list[first] = list[high];
          list[high] = pivot;
          return high;
        }
        else {
          return first;
        }
      }

    // HEAP SORT

  /** Heap sort method */
//
//    public static <E extends Comparable<E>> void heapSort(E[] list) 
//    {
//      // Create a Heap of integers
//      Heap<E> heap = new Heap<>();
//
//      // Add elements to the heap
//      for (int i = 0; i < list.length; i++)
//        heap.add(list[i]);
//
//      // Remove elements from the heap
//      for (int i = list.length - 1; i >= 0; i--)
//        list[i] = heap.remove();
//    }
//
//public class Heap<E extends Comparable<E>> {
//  private java.util.ArrayList<E> list = new java.util.ArrayList<>();
//
//  /** Create a default heap */
//  public Heap() {
//  }
//
//  /** Create a heap from an array of objects */
//  public Heap(E[] objects) {
//    for (int i = 0; i < objects.length; i++)
//      add(objects[i]);
//  }
//
//  /** Add a new object into the heap */
//  public void add(E newObject) {
//    list.add(newObject); // Append to the heap
//    int currentIndex = list.size() - 1; // The index of the last node
//
//    while (currentIndex > 0) {
//      int parentIndex = (currentIndex - 1) / 2;
//      // Swap if the current object is greater than its parent
//      if (list.get(currentIndex).compareTo(
//          list.get(parentIndex)) > 0) {
//        E temp = list.get(currentIndex);
//        list.set(currentIndex, list.get(parentIndex));
//        list.set(parentIndex, temp);
//      }
//      else
//        break; // the tree is a heap now
//
//      currentIndex = parentIndex;
//    }
//  }
//
//  /** Remove the root from the heap */
//  public E remove() {
//    if (list.size() == 0) return null;
//
//    E removedObject = list.get(0);
//    list.set(0, list.get(list.size() - 1));
//    list.remove(list.size() - 1);
//
//    int currentIndex = 0;
//    while (currentIndex < list.size()) {
//      int leftChildIndex = 2 * currentIndex + 1;
//      int rightChildIndex = 2 * currentIndex + 2;
//
//      // Find the maximum between two children
//      if (leftChildIndex >= list.size()) break; // The tree is a heap
//      int maxIndex = leftChildIndex;
//      if (rightChildIndex < list.size()) {
//        if (list.get(maxIndex).compareTo(
//            list.get(rightChildIndex)) < 0) {
//          maxIndex = rightChildIndex;
//        }
//      }
//
//      // Swap if the current node is less than the maximum
//      if (list.get(currentIndex).compareTo(
//          list.get(maxIndex)) < 0) {
//        E temp = list.get(maxIndex);
//        list.set(maxIndex, list.get(currentIndex));
//        list.set(currentIndex, temp);
//        currentIndex = maxIndex;
//      }
//      else
//        break; // The tree is a heap
//    }
//
//    return removedObject;
//  }
//
//  /** Get the number of nodes in the tree */
//  public int getSize() {
//    return list.size();
//  }
//  
//  /** Return true if heap is empty */
//  public boolean isEmpty() {
//    return list.size() == 0;
//  }
//}  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
//        System.out.println("\n\n\n ORIGINAL LIST\n\n");
//        for (int sub = 0; sub < origList.length; sub++)
//        {
//            if (sub == 0 || sub % displayCount != 0)
//                System.out.print(origList[sub] + " ");
//            else
//                System.out.println(origList[sub] + " ");
//        }
//        
//        System.out.println("\n\n\n COPIED LIST\n\n");
//        for (int sub = 0; sub < list.length; sub++)
//        {
//            if (sub == 0 || sub % displayCount != 0)
//                System.out.print(list[sub] + " ");
//            else
//                System.out.println(list[sub] + " ");
//        }

        System.out.println( "\n\n Arraysize | Selection Sort   Radix Sort   Bubble Sort    Merge Sort   Quick Sort   Heap Sort");
        System.out.println("-----------|------------------------------------------------------------------------------------");
        int numInArray = 50000;
        
        int randomRange = (99999);
        int displayCount = 10;  //  number of elements in each line of print
        int largestNumber = 0;
        
        String spacing = " ";
        int printSpaces = (int) (Math.log10(largestNumber) + 1);
        for (int sub = 1; sub <= 6;  sub++)
        {
                              

     
            // System.out.println("random Range = " + randomRange + "\n\n");

            // create origList to hold however many numbers specified in
            // numInArray.

            int[] origList = new int[numInArray]; // kept for each sort procedure
            int[] list = new int[numInArray];   // copied and to be sorted.

                // load the list with randoms between 0 and 999
            for (int idx = 0; idx < origList.length; idx++){
                origList[idx] = (int)(Math.random() * randomRange);
                if (origList[idx] > largestNumber){
                    largestNumber = origList[idx];
                } // end of if
            } // end of for loop

            // create a copy of origList for the sort procedures to sort and 
            // log times to sort.

            list = Arrays.copyOf(origList,origList.length);

            // System.out.println("\n\n\n largest Number = " + largestNumber + "\n");
            // get the length of the largest number
            int numDigits = (int) (Math.log10(largestNumber) + 1);
            if (numInArray == 50000)
            {
               spacing =  "    |      ";
            }
            else
            {
                spacing = "   |      ";
            }
            String printLine = "  " + numInArray + spacing;

            // the remainder will be performed six times
            // at the bottom of the loop, 50,000 will be added to
            //    
            list = Arrays.copyOf(origList,origList.length);
            long startTime = System.currentTimeMillis();
            selectionSort(list);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            printSpaces = (int) (Math.log10(executionTime) + 1);
            if (printSpaces == 4)
            {
                spacing = "            ";
            }
            else
                if (printSpaces == 5)
                {
                spacing = "           ";
                }
                else
                    spacing = "          ";
            
            printLine += executionTime + spacing;

            // System.out.println(printLine);

            list = Arrays.copyOf(origList,origList.length);
            startTime = System.currentTimeMillis();
            radixSort(list, numDigits);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            printSpaces = (int) (Math.log10(executionTime) + 1);
            if (printSpaces == 2)
            {
                spacing = "          ";
            }
            else
                if (printSpaces == 3)
                {
                spacing = "         ";
                }
                else
                    spacing = "        ";
            
            printLine += executionTime + spacing;
         

            list = Arrays.copyOf(origList,origList.length);
            startTime = System.currentTimeMillis();
            bubbleSort(list);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            printSpaces = (int) (Math.log10(executionTime) + 1);
            if (printSpaces == 4)
            {
                spacing = "            ";
            }
            else
                if (printSpaces == 5)
                {
                spacing = "           ";
                } 
                else
                    if (printSpaces == 6)
                        spacing = "          ";
                    else
                        spacing = "         ";
            printLine += executionTime + spacing;

            list = Arrays.copyOf(origList,origList.length);
            startTime = System.currentTimeMillis();
            mergeSort(list);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            printLine += executionTime + "           ";

            list = Arrays.copyOf(origList,origList.length);
            startTime = System.currentTimeMillis();
            quickSort(list);
            endTime = System.currentTimeMillis();
            executionTime = endTime - startTime;
            printLine += executionTime + "       ";

            System.out.println(printLine);
            
            // and 50,000 to num in array and do the for loop again.
            numInArray += 50000;
            
        }   //  end of for loop.
        
        //  print a few blank lines at end of program.
        
        System.out.println("\n\n\n");
    }   // end of main
    
    
}   // end of package.

