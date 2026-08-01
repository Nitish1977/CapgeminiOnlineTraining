package Threads.Runnable;

public class Thread2 implements Runnable{
    @Override
    public void run() {
        method2();

    }

    public static void method2(){
        for(int i=0; i<=5 ; i++){
            System.out.println("Method2 "+Thread.currentThread().getName());
        }
    }
}
