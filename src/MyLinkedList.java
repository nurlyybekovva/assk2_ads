import java.util.Iterator;
import java.util.NoSuchElementException;
public class MyLinkedList<T> implements MyList<T>{

    class MyNode<E> {
        E element;  // The data of the node
        MyNode next;  // The reference to the next node
        MyNode prev;  // The reference to the previous node

        // Constructor with 1 parameter(element):
        public MyNode(E element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
        // Constructor with 3 parameters(element, next, prev):
        public MyNode(E element, MyNode next, MyNode prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
        // Constructor with no parameter:
        public MyNode(){
            this.element = null;
            this.next = null;
            this.prev = null;
        }
    } // Node class

    private MyNode head; // The reference to the first node
    private MyNode tail; // The reference to the last node
    private int size; // The size of list, number of elements in the list

    public MyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    } // Constructor(no parameter)

    private void checkCapacity(int i){
        if (i < 0 || i >= size){
            throw new IndexOutOfBoundsException("Index: " + i + " and Size: " + size);
        }
    } // Checks if the given index is in the range of the list

    @Override
    public void add(T item) {
        MyNode newNode = new MyNode(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }  // Appends the element to the element of the list

    @Override
    public void set(int index, T item) {
        checkCapacity(index);
        MyNode current = head;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        current.element = item;
    } // Replaces the element at the specified position with the specified element

    @Override
    public void add(int index, T item) {
        checkCapacity(index);
        MyNode newNode = new MyNode(item);
        if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        else {
            MyNode current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        size++;
    } // Inserts the element at the specified position in the list

    @Override
    public void addFirst(T item) {
        add(0, item);
    } // Inserts the element at the top of list where index = 0

    @Override
    public void addLast(T item) {
        add(item);
    } // Adds the element to the end of the list

    @Override
    public T get(int index) {
        checkCapacity(index);
        MyNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return (T) current.element;
    } // Returns the element at the specified position in the list

    @Override
    public T getFirst() {
        return (T) head.element;
    } // Returns the first element(head) in the list

    @Override
    public T getLast() {
        return (T) tail.element;
    } // Returns the last element(tail) in the list

    @Override
    public void remove(int index) {
        checkCapacity(index);
        if (index == 0) {
            head = head.next;
            head.prev = null;
        }
        else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        }
        else {
            MyNode current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
    } // Removes the element at the specified position

    @Override
    public void removeFirst() {
        remove(0);
    } // Removes the first element(index = 0)

    @Override
    public void removeLast() {
        remove(size - 1);
    } // Removes the last element

    @Override
    public void sort() {
        for (MyNode i = head; i != null; i = i.next) {
            for (MyNode j = i.next; j != null; j = j.next) {
                if (((Comparable)j.element).compareTo(i.element) < 0) {
                    Object current = i.element;
                    i.element = j.element;
                    j.element = current;
                }
            }
        }
    } // Sorting in ascending order

    @Override
    public int indexOf(Object object) {
        MyNode current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(object)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    } // Returns the index of the first occurrence of the element in the list, and if the list doesn't contain the element it returns -1

    @Override
    public int lastIndexOf(Object object) {
        MyNode current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.element.equals(object)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    } // Returns the index of the last occurrence of the element in the list, if the list doesn't contain the element it returns -1

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    } // Returns true if the list contains the element

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        MyNode current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.element;
            current = current.next;
        }
        return arr;
    } // Returns an array containing all elements  in the list in sequence

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    } // Removes all elements from the list

    @Override
    public int size() {
        return size;
    } // Returns the size of the list(number of elements)

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    } // Returns an iterator over the elements in the list in sequence

    public class MyIterator implements Iterator<T>{
        private MyNode current = head;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (hasNext() != true) {
                throw new NoSuchElementException();
            }
            T element = (T) current.element;
            current = current.next;
            index++;
            return element;
        }
    }  // Iterator class
}
