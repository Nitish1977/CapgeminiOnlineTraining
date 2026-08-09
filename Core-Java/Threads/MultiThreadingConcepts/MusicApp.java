package Threads.MultiThreadingConcepts;

public class MusicApp {
    public void play(){
        for(int i =0; i<= 10; i++){
            System.out.println(i+" Play ");
        }
    }

    public void stop(){
        for(int i =0; i<= 10; i++){
            System.out.println(i+" Stop ");
        }
    }
}
