package OOPs.BasicBeforeOOpsConcept;

import java.sql.SQLOutput;

public class Blocks {

    Blocks(){

        System.out.println("Constructor");

    }
    { // it will trigger after creation of object of a class but before constructor
        System.out.println("Non - Static block");
    }
    static { // it will trigger when class is loaded
        System.out.println("Static block");
    }



    public static void main(String[] args) {

        System.out.println("===Main Starts===");


        System.out.println("===Object creation===");
        Blocks obj = new Blocks();
        System.out.println("===Main ends===");

    }
}
