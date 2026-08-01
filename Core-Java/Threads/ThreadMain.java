package Threads;

public class ThreadMain {
    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();


        t1.start();  // instead of calling run() method we have to call start()
        // we call directly with run() it will controlled by main thread.
        t2.start();
    }
}
