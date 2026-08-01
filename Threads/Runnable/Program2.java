package Threads.Runnable;

import javax.xml.transform.Source;

public class Program2 {

    public static void main(String[] args) {


        System.out.println("Main Start");

        Runnable r1 = () ->{
            for(int i=0; i<=5 ; i++){
                System.out.println("Method1 "+Thread.currentThread().getName());
            }

        };

        Runnable r2 = ()->{
            for(int i=0; i<=5 ; i++){
                System.out.println("Method2 "+Thread.currentThread().getName());
            }

        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();


        System.out.println("Main End");
    }
}
