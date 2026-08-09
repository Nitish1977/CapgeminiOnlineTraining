package Threads.MultiThreadingConcepts;

import Threads.Thread2;

public class Program1 {

    public static void main(String[] args)throws InterruptedException{
        //StringBuilder str1 = new StringBuilder(); // there is an exception: it will return different length everytime.
        StringBuffer str1 = new StringBuffer();

        Thread t1 = new Thread(
                ()->{
                    for(int i=0; i<1000; i++){
                        str1.append(i);
                    }
                }
        );

        Thread t2 = new Thread(
                ()->{
                    for(int i=0; i<1000; i++){
                        str1.append(i);
                    }
                }
        );


        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(str1.length());

    }
}
