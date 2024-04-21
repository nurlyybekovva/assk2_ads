import java.util.Iterator;

public class MyArrayList<T extends Comparable<T>> implements MyList<T>{


    private static Object[] arr; // The array into which the elements of the MyArrayList are stored
    private int length  = 0; // The number of elements
    private static final int DEFAULT_CAPACITY = 10; // The default initial capacity
    public static void increaseCapacity(){
        Object[] temp = new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++){
            temp[i] = arr[i];
        }
        arr = temp;
    } // Increases the capacity of the MyArrayList instance, if it necessary
    private void checkCap(int index){
        if (index < 0 || index >= length){
            throw new IndexOutOfBoundsException(index);
        }
    } // Checks if the given index in the range of the list

    public MyArrayList(int initialCapacity){
        arr = new Object[initialCapacity];
    } // An empty list with the specified initial capacity
    public MyArrayList(){
        this(DEFAULT_CAPACITY);
        length  = 0;
    } // An empty list with an initial capacity of ten

    @Override
    public void add(T item) {
        if (length == arr.length){
            increaseCapacity();
        }
        Object[] temp = new Object[length + 1];
        for (int i = 0; i < length; i++){
            temp[i] = arr[i];
        }
        temp[length] = item;
        length++;
        arr = temp;
    } // Adds the element to the end of the list

    @Override
    public void set(int index, T item) {
        checkCap(index);
        arr[index] = item;
    } // Replaces the element at the specified position with the other element

    @Override
    public void add(int index, T item) {
        checkCap(index);
        if (length == arr.length){
            increaseCapacity();
        }
        Object[] temp = new Object[length + 1];
        for (int i = 0; i < index; i++){
            temp[i] = arr[i];
        }
        temp[index] = item;
        for (int i = index + 1; i < length + 1; i++){
            temp[i] = arr[i-1];
        }
        length++;
        arr = temp;
    } // Inserts the element at the specified element if the list

    @Override
    public void addFirst(T item) {
        add(0,item);
    } // Inserts the element at the beginning of the list

    @Override
    public void addLast(T item) {
        add(item);
    } // Inserts the element to the end of the list

    @Override
    public T get(int index) {
        checkCap(index);
        return (T)arr[index];
    } // Returns the element at the specified position in this list

    @Override
    public T getFirst() {
        return get(0);
    } // Returns the first element in the list

    @Override
    public T getLast() {
        return get(length - 1);
    } // Returns the last element in the list

    @Override
    public void remove(int index) {
        checkCap(index);
        Object[] temp = new Object[length - 1];
        for (int i = 0; i < index; i++){
            temp[i] = arr[i];
        }
        for (int i = index; i < length - 1; i++){
            temp[i] = arr[i+1];
        }
        length--;
        arr = temp;
    } // Removes the element at the specified position in the list

    @Override
    public void removeFirst() {
        remove(0);
    } // Removes the first element from the list

    @Override
    public void removeLast() {
        remove(length-1);
    } // Removes the last element from the list

    @Override
    public void sort() {
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length-1-i; j++){
                if (((Comparable)arr[j]).compareTo(arr[j+1]) > 0){
                    Object temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    } // Sorting in ascending order

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < length; i++){
            if (arr[i].equals(object)){
                return i;
            }
        }
        return -1;
    } // Returns the index of the first occurrence of the element in the list, and if the element doesn't exist it returns -1

    @Override
    public int lastIndexOf(Object object) {
        for (int i = length - 1; i >= 0; i--){
            if (arr[i].equals(object)){
                return i;
            }
        }
        return -1;
    }  // Returns the index of the last occurrence of the element in the list, and if the element doesn't exist it returns -1

    @Override
    public boolean exists(Object object) {
        if (indexOf(object) != -1){
            return true;
        }
        return false;
    } // Returns true if the list contains the specified element

    @Override
    public Object[] toArray() {
        Object[] temp = new Object[length];
        System.arraycopy(arr, 0, temp, 0, length);
        return temp;
    } // Returns an array containing all elements in this list in proper sequence

    @Override
    public void clear() {
        Object[] temp = new Object[DEFAULT_CAPACITY];
        arr = temp;
    } // Removes all elements from the list

    @Override
    public int size() {
        return length;
    } // Returns the number of elements in the list

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    } // Returns an iterator over the elements in this list in proper sequence

    public class MyIterator implements Iterator<T>{
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public T next() {
            return (T) arr[index++];
        }
    } // An iterator over the elements in the list in proper sequence
}
