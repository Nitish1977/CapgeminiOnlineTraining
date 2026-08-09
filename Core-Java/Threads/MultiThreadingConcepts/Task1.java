package Threads.MultiThreadingConcepts;

import javax.swing.plaf.TableHeaderUI;
import java.util.*;

// check arrayList is Synchronized or not
public class Task1{

    public static void main(String[] args)throws InterruptedException   {

        System.out.println("Array List size: " );
        testArrayList();

        System.out.println("HashSet Size: ");
        testHashSet();

        checkLinkedList();



    }
    public static void testArrayList() throws InterruptedException{


        List<Integer> list = new ArrayList<>();

        Thread t1 = new Thread(
                ()->{
                    for(int i=0; i<100; i++){
                        list.add(i);
                    }
                }
        );

        Thread t2 = new Thread(
                ()->{
                    for (int i =0; i<100; i++){
                        list.add(i);
                    }
                }
        );

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(list.size());
    }

    public static void testHashSet() throws InterruptedException{


        Set<Integer> hashSet = new HashSet<>();

        Thread t1 = new Thread(
                ()->{
                    for(int i=0; i<100; i++){
                        hashSet.add(i);
                    }
                }
        );

        Thread t2 = new Thread(
                ()->{
                    for (int i =0; i<100; i++){
                        hashSet.add(i);
                    }
                }
        );

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(hashSet.size());
    }

    public static void checkLinkedList() throws InterruptedException{
        System.out.println("Check LinkedList");

        List<Integer> queue = new LinkedList<>();

        Thread t1 = new Thread(
                ()->{
                    for(int i=0; i<=100; i++){
                        queue.add(i);
                    }
                }
        );

        Thread t2 = new Thread(
                ()->{
                    for(int i=0; i<=100; i++){
                        queue.add(i);
                    }
                }
        );

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(queue.size());
    }
}
