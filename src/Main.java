import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        MyArrayList<Integer> myArrayList = new MyArrayList<>() ;
        myArrayList.add(1);
        myArrayList.add(2);
        myArrayList.add(3);
        myArrayList.add(4);
        myArrayList.add(5);
        myArrayList.add(6);

        myArrayList.addLast(30);
        for (int i = 0; i < myArrayList.size(); i++){
            System.out.println(myArrayList.get(i));
        }
    }
}
