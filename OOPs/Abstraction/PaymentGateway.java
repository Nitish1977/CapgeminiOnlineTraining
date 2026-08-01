package OOPs.Abstraction;
// An interface in java a core blueprint of a class. it is used to achieve 100% abstraction and multiple inheritance in java.
// an interface contains static constants, and abstract methods. it tells a call, what to do but not how to do it.
// default method, static methods, private methods,
public interface PaymentGateway {

    void pay();

    String msg = "Welcome To Payment Gateway";
}

class UPI implements PaymentGateway{
    @Override
    public void pay() {
        System.out.println("Payment Successful by UPI");
    }

}
