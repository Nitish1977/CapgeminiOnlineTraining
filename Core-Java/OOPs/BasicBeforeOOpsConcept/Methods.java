package OOPs.BasicBeforeOOpsConcept;

import java.sql.SQLOutput;

public class Methods {

    public static void display(){
        System.out.println("Static Method(Display)");
    }

    public void output(){
        System.out.println("non-Static method(Output)");
    }
    private void aPrivate(){ //visible only within the same class
        System.out.println("Private method");
    }

    void defaultMethod(){ // visible within the same package
        System.out.println("Default Method");
    }

    final void finalMethod(){  // unchangeable or readonly
        System.out.println("Final method");
    }

    //methods are: method with no parameter no return type, method with no parameter with return type,
    //method with parameter no return type, and last with parameter with return type.

    public static void main(String[] args) {
        display();
        //output(); error, we cannot call the non-static method directly we have to use object of a class

        Methods obj = new Methods();
        obj.output();
        obj.aPrivate();
        obj.defaultMethod();
        obj.finalMethod();
    }
}
