package Collections.Set;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

// it maintains the insertion order
public class linkedHashSet {
    public static void main(String[] args) {
        Set<Integer> linkedSet1 = new LinkedHashSet<>();
        linkedSet1.add(1);
        linkedSet1.add(23);
        linkedSet1.add(24);
        linkedSet1.add(99);
        linkedSet1.add(null);
        linkedSet1.add(-1);
        linkedSet1.add(null);
        linkedSet1.add(-23);


        System.out.println(linkedSet1);
        System.out.println("==== For Each ====");

        linkedSet1.forEach(System.out::println);

        System.out.println("=== for Loop ===");

        Iterator<Integer> itr = linkedSet1.iterator();


        System.out.println("=== Iterator ===");

        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
