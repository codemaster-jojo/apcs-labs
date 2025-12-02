
/**
 * Heap utility class with a bunch of heap methods
 *
 * @author Jonny Tang
 * @version 11/20/2025
 */
public class HeapUtil
{
    // instance variables - replace the example below with your own
    private int heapSize = 0;

    /**
     * Constructor for objects of class HeapUtil
     * 
     * @param hs the heap size
     */
    public HeapUtil(int hs)
    {
        heapSize = hs;
    }
    
    /**
     * Swaps 2 values in array
     * 
     * @param heap the array
     * @param index1 first element
     * @param index2 second element
     */
    private void swap(Comparable[] heap, int index1, int index2)
    {
        Comparable tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }
    
    /**
     * heapify method to make an array into heap.
     * 1. start with root node, swap with child if necessary 
     *    to make root node largest
     * 2. call heapify again on the child that got swapped recursively
     * 3. repeat
     * 
     * remember everything is 1-indexed
     * 
     * @param heap the heap
     * @param index the index that is the root getting heapified
     */
    public void heapify(Comparable[] heap, int index)
    {        
        int left = index*2;
        int right = index*2 + 1;
        int largest = index;
        
        if (left <= heapSize && heap[left].compareTo(heap[largest]) > 0)
        {
            largest = left;
        }
        if (right <= heapSize && heap[right].compareTo(heap[largest]) > 0)
        {
            largest = right;
        }
        
        if (largest == index)
        {
            return;
        }
        
        swap(heap, index, largest);
        heapify(heap, largest);
    }
    
    /**
     * Builds heap by taking first parent node and heapifying
     * and repeating until you reach root nod
     * 
     * @param heap the heap 
     */
    public void buildHeap(Comparable[] heap)
    {
        
        for (int i = heapSize / 2; i >= 1; i--)
        {
            heapify(heap, i);
        }
    }
    
    /**
     * Removes node from heap and makes sure its still a heap
     * Then size-1
     * 
     * @param heap the heap
     * @return new root node
     */
    public Comparable remove(Comparable[] heap)
    {
        Comparable removed = heap[1];
        
        heap[1] = heap[heapSize];
        heap[heapSize] = null;
        
        heapSize--;
        
        // no need to rebuild heap just need to heapify
        heapify(heap, 1);
        
        return removed;
    }
    
    /**
     * Inserts value from heap and makes sure its still a heap
     * 
     * @return the resulting heap
     * 
     * @param heap the heap
     * @param item the item getting inserted
     */
    public Comparable[] insert(Comparable[] heap, Comparable item)
    {
        Comparable[] result = heap;
        if (heapSize + 1 >= heap.length)
        {
            Comparable[] newHeap = new Comparable[heap.length * 2];

            for (int i = 1; i <= heapSize; i++)
            {
                newHeap[i] = heap[i];
            }
            result = newHeap;
        }
        
        heapSize++;
        result[heapSize] = item;
        heap = result;
        
        buildHeap(heap);
        
        return heap;
    }
    
    /**
     * Heap sort
     * 1. heapify the array
     * 2. remove first value and put in new array
     * 3. heap size -1
     * 4. repeat
     */
    public Comparable[] heapSort(Comparable[] heap)
    {
        Comparable[] copy = new Comparable[heapSize + 1];
        for (int i = 1; i <= heapSize; i++)
        {
            copy[i] = heap[i];
        }
        
        Comparable[] sorted = new Comparable[heapSize+1];
        int originalSize = heapSize;
        
        // sort in ascending order im assuming? 
        for (int i = originalSize; i >= 1; i--)
        {            
            sorted[i] = remove(copy);
        }
        
        heapSize = originalSize;
        
        return sorted;
    }
}