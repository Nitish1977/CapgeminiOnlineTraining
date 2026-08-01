package Exceptions;
class InvalidAgeException extends RuntimeException{
    public InvalidAgeException(String message){
        super(message);
    }
}
public class CustomExceptionRunTime {
    public static void main(String[] args) {
        try{
            checkAge(12);
        }catch (InvalidAgeException e){
            System.out.println(e.getMessage());
        }
    }
    public static void checkAge(int age){
        if(age<18){
            throw new InvalidAgeException("Access Denied: Age Must be greater than 18");
        }else{
            System.out.println("Access Granted!!");
        }
    }
}
