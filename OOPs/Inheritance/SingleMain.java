package OOPs.Inheritance;

//single-level Inheritance: there is only one super class

public class SingleMain {

    public static void main(String[] args) {
        SingleChild obj = new SingleChild();
        obj.display();
        obj.print();
        // subclass object able to get the property of the super class as well as its own.
    }
}
