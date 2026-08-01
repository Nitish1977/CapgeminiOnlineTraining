package Collections.List;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class linkedList {
    public static void main(String[] args) {
        List<String> name = new LinkedList<>();
        name.add("Mohan");
        name.add("Hari");
        name.add("Mehul");
        name.add("Hari");

        System.out.println("Output");
        System.out.println(name);

        System.out.println("=== for loop ====");

        for (String i: name){
            System.out.println(i);
        }

        System.out.println("===ForEach===");
        name.forEach(System.out::println);

        System.out.println("=== iterator ===");
        Iterator<String> itr = name.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
