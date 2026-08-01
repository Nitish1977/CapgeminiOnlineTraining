package OOPs.BasicBeforeOOpsConcept;


//constructor:
// It is a special type of method that is used to initialize the values while creating an object of a class.
// name of the constructor is same as a className
// it has no return type
// constructor overloading is possible, we will se in further
//constructor overriding is not possible.
// if we are not creating any constructor then the compiler automatic create one no args constructor internally.

public class ConstructorMain {
    public static void main(String[] args) {
        Constructor obj = new Constructor("Nitish", 22, 7.8);
        System.out.println(
                "Name: "+obj.name
                +" Roll: "+obj.roll
                +" CGP:  "+obj.CGP
        );
    }
}
