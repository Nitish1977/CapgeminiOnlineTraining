package Collections.List;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class arrayList {

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList(Arrays.asList(12,3,4,766,2,11,2,3));

        System.out.println("list traversing");
        System.out.println(arr);

        System.out.println("for loop");
        for(int i =0; i<arr.size(); i++){
            System.out.println(arr.get(i));
        }

        System.out.println("=== forEach ===");
        arr.forEach(System.out::println);

        System.out.println("=== List iterator ===");
        Iterator itr = arr.listIterator();

        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        System.out.println("=== with iterator===");
        Iterator itr2 = arr.iterator();
        while(itr2.hasNext()){
            System.out.println(itr2.next());
        }

    }
}
