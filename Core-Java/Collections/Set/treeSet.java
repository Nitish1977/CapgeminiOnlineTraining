package Collections.Set;

import java.util.*;

public class treeSet {


    // sorted order: by default ascending order

    public static void main(String[] args) {
        Set<Integer> num = new TreeSet<>();
        num.add(1);
        num.add(-1);
        num.add(99);
        num.add(23);
        num.add(33);
        num.add(-1);
        num.add(-5);
        num.add(23);

        System.out.println("=== Data ===");
        System.out.println(num);


        System.out.println("=== for loop ===");
        for ( int i : num ){
            System.out.println(i);
        }


        System.out.println("=== For Each Loop ====");
        num.forEach(System.out::println);

        System.out.println("=== Iterator ===");
        Iterator<Integer> itr = num.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

    }
}
