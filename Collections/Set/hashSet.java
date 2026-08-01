package Collections.Set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


// Set: stores Unique elements
public class hashSet {
    // use hashMapping : hashCode%16 = rem;
    // insertion order not there.


    public static void main(String[] args) {
        Set<Integer> num = new HashSet<>();
        num.add(1);
        num.add(32);
        num.add(null);
        num.add(0);
        num.add(22);
        num.add(-1);
        num.add(-21);
        num.add(null);
        num.add(30);

        System.out.println(num);

        System.out.println("=== forEach ===");
        num.forEach(System.out::println);

        System.out.println("== iterator ==");
        Iterator itr = num.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

    }

}
