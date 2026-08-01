package OOPs.Abstraction;


// multiple inheritance is not possible with class, but we can achieve it with the help of interfaces

public class MultipleInheritance {

    public static void main(String[] args) {

        P1.msg();
        P2.message();

        Operations obj = new Operations();
        obj.run();
        obj.play();
        obj.pause();
        obj.close();




    }
}
