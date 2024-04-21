public class MyMinHeap<T extends Comparable<T>> {
    MyArrayList<T> heap = new MyArrayList<>();
    // Based on MyArrayList

    public MyMinHeap(){
    } // Constructor

    public int size(){
        return heap.size();
    } // Returns size of the heap

    public void clear(){
        heap.clear();
    } // Removes all elements from the heap

    public T getMin(){
        return heap.getFirst();
    } // First element


    public T extractMin(){
        T min = heap.getFirst();
        swap(0, heap.size()-1);
        heap.removeLast();
        heapify(0);
        return min;
    } // Gets minimum and swaps with last element and removes last element

    public void insert(T item){
        heap.add(item);
        int i = heap.size() - 1;
        heapify(i);
    } // Inserts new item to the end of the heap and puts it to the correct place

    private void heapify(int i){
        int left = leftChildOf(i);
        int right = rightChildOf(i);
        int minimum = i;
        if (left < heap.size() && heap.get(left).compareTo(heap.get(minimum)) < 0){
            minimum = left;
        }
        if (right < heap.size() && heap.get(right).compareTo(heap.get(minimum)) < 0){
            minimum = right;
        }
        if (minimum != i){
            swap(i, minimum);
            heapify(minimum);
        }
    } // heapify function to maintain the heap property after deletion or insertion

    private void traverse(int i){
        if (i < heap.size()){
            System.out.println(heap.get(i));
            traverse(leftChildOf(i));
            traverse(rightChildOf(i));
        }
    } // traverse function to print the heap
    public int leftChildOf(int i){
        return 2 * i;
    } // returns left child of parent
    public int rightChildOf(int i){
        return 2 * i + 1;
    } // Returns right child of parent
    public int parentOf(int i){
        return i / 2;
    } // Returns parent
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    } // Swapping of two elements
}
