package Threads.Runnable;

public class Thread1 implements  Runnable{
    @Override
    public void run() {
        method1();

    }

    public static void method1(){
        for(int i=0; i<=5 ; i++){
            System.out.println("Method1 "+Thread.currentThread().getName());
        }
    }


}
