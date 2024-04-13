import java.util.Iterator;

public class MyArrayList<T extends Comparable<T>> implements MyList<T>{


    private static Object[] arr;
    private int length  = 0;
    private static final int DEFAULT_CAPACITY = 10;
    public static void increaseCapacity(){
        Object[] temp = new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++){
            temp[i] = arr[i];
        }
        arr = temp;
    }
    private void checkCap(int index){
        if (index < 0 || index >= length){
            throw new IndexOutOfBoundsException(index);
        }
    }

    public MyArrayList(int initialCapacity){
        arr = new Object[initialCapacity];
    }
    public MyArrayList(){
        this(DEFAULT_CAPACITY);
        length  = 0;
    }

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
    }

    @Override
    public void set(int index, T item) {
        checkCap(index);
        arr[index] = item;
    }

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
    }

    @Override
    public void addFirst(T item) {
        add(0,item);
    }

    @Override
    public void addLast(T item) {
        add(length, item);
    }

    @Override
    public T get(int index) {
        checkCap(index);
        return (T)arr[index];
    }

    @Override
    public T getFirst() {
        return get(0);
    }

    @Override
    public T getLast() {
        return get(length - 1);
    }

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
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(length-1);
    }

    @Override
    public void sort() {
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length; j++){
                if (((Comparable)arr[j]).compareTo(arr[j+1]) > 0){
                    Object temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < length; i++){
            if (arr[i].equals(object)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = length - 1; i >= 0; i--){
            if (arr[i].equals(object)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        if (indexOf(object) != -1){
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return arr;
    }

    @Override
    public void clear() {
        Object[] temp = new Object[DEFAULT_CAPACITY];
        arr = temp;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

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
    }
}
