package Collections.Queue;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class priorityQueue {

    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.add(3);
        pq.add(10);
        pq.add(7);
        pq.add(3);
        pq.add(2);

        System.out.println(pq);



        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.add(3);
        ad.add(10);
        ad.add(7);
        ad.add(3);
        ad.add(2);

        System.out.println(ad);
    }
}
