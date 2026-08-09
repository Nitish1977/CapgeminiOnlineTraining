package Threads.MultiThreadingConcepts;

import java.util.ArrayList;

public class Program2 {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();

        for(int j =0; j<10; j++){
            numbers.add(j);

            
            //single stream
            //parallel streams

            numbers.parallelStream()
                    .forEach(i->{
                        System.out.println(Thread.currentThread().getName()+" : "+i);
                    });
        }
    }
}
