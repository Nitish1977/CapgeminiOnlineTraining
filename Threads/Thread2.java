package Threads;

public class Thread2 extends Thread{

    @Override
    public void run(){ // this run method present inside the Thread classes
        method2();

    }
    public static void method2(){
        for(int i=0; i<=5 ; i++){
            System.out.println("Method2 "+i);
        }
    }
}
