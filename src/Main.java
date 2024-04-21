import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        MyArrayList<String> myList = new MyArrayList<>();

        myList.add("Nur-Sultan");
        myList.add("Almaty");
        myList.add("Shymkent");
        myList.add("Turkistan");
        printArray(myList);

        myList.set(0, "Astana");
        printArray(myList);

        myList.add(1,"Turkistan");
        printArray(myList);

        myList.addFirst("Oskemen");
        printArray(myList);

        myList.addLast("Karagandy");
        printArray(myList);


        System.out.println(myList.get(3));


        System.out.println(myList.getFirst());


        System.out.println(myList.getLast());


        myList.remove(6);
        printArray(myList);


        myList.removeFirst();
        printArray(myList);


        myList.removeLast();
        printArray(myList);


        System.out.println("Check sorting");
        myList.sort();
        printArray(myList);


        System.out.println(myList.indexOf("Oskemen"));


        System.out.println(myList.lastIndexOf("Turkistan"));


        System.out.println(myList.exists("Karagandy"));


        myList.clear();
        printArray(myList);


        System.out.println(myList.size());
    }

    public static void printArray(Iterable<String> arr) {
        for (Object object : arr) {
            System.out.print(object + " ");
        }
        System.out.println();
    }
}
