package OOPs.Abstraction;

public class Operations implements P1, P2{

    @Override
    public void run() {
        System.out.println("Application is running...");
    }

    @Override
    public void play() {
        System.out.println("video is playing..");


    }

    @Override
    public void pause() {
        System.out.println("video is paused");

    }

    @Override
    public void close() {
        System.out.println("Application is closed");

    }
}
