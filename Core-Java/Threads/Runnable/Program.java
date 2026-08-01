package Threads.Runnable;

public class Program {

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        Thread thread1 = new Thread(t1);
        thread1.setName("A");
        Thread thread2 = new Thread(t2);
        thread2.setName("B");


        thread1.start();
        thread2.start();

    }
}
