package Exceptions;

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

public class CustomException {

    public static void main(String[] args) {
        try {
            callMethod();
        } catch (InvalidAmountException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void callMethod() throws InvalidAmountException{
        withdraw(-2);
    }

    public static void withdraw(double amount)throws InvalidAmountException  {
        if (amount <= 0) {
            throw new InvalidAgeException("Your Request is rejected. Please enter an amount greater than 0.");
        } else {
            System.out.println("Withdraw is Completed");
        }
    }

    // throw is used to throw an exception
    // throws is used method declaration, if method may have exception. Used only when the exception is compile time exception
    // not use for runtime
}
