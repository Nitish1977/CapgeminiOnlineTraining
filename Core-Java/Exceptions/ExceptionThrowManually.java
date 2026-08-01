package Exceptions;
// we can throw exception explicitly in our code using throw keyword
public class ExceptionThrowManually {

    public static void main(String[] args) {
        checkAge(12);
        checkAge(23);

    }

    public static void checkAge(int age){
        if(age<18){
            throw new IllegalArgumentException("Access denied: Age Must be greater than 18.");
        }else{
            System.out.println("Access Granted");
        }
    }
}
