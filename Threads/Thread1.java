package Threads;

public class Thread1 extends Thread {


    @Override
    public void run(){ // this run method present inside the Thread classes
      method1();
    }

    public static void method1(){
        for(int i=0; i<=5 ; i++){
            System.out.println("Method1 "+i);
        }
    }
}
