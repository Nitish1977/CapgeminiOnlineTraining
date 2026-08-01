package OOPs.Abstraction;

import OOPs.BasicBeforeOOpsConcept.Methods;

public class InterfaceMain {
    public static void main(String[] args) {
        PaymentGateway method = new UPI();
        System.out.println(method.msg);
        method.pay();
    }
}
