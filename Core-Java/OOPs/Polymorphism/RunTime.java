package OOPs.Polymorphism;

import java.sql.SQLOutput;

public class RunTime {

    // it is also called dynamic polymorphism
    // it is implements with method overriding.
    // Overriding;
    public static void main(String[] args) {

        Dog obj = new Dog();
        obj.sound();

        System.out.println(obj.a+" : "+obj.b);
        // if i want to access only parent members then
        Animal obj2 = new Dog();
        System.out.println(obj2.a);
       // System.out.println(obj2.b); // give compile time error
        obj2.sound(); // it will work but it calls child class sound method
        //because sound method is overridden by the child class at runtime.

    }
}
