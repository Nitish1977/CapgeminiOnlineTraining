package OOPs.Abstraction;



// abstract class cannot be instantiated directly using new. it contains both abstract method and normal method.
abstract class Vehicle {

    abstract void startEngine(); // it is an abstract method, a method have only signature, it have no body.
    void fuel(){
        System.out.println("Filling fuel tank....");
    }
}

class Car extends Vehicle{
    @Override
    void startEngine() {
        System.out.println("Car engine is started...");
    }
}
