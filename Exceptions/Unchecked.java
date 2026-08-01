package Exceptions;
// unchecked exception: exceptions that occurs during program execution. the compiler does not force us to handle them,
// but they can crash our application if we ignore
// Example: NullPointer, ArithmeticException, ArrayIndexOutOfBoundException, IllegalArgument

public class Unchecked {
    public static void main(String[] args) {


        try{ // wrap code that might throw an exception
            int result = 10/0;
            System.out.println(result);
        }catch (ArithmeticException e){ // to handle the exception
            System.out.println("Error: "+e);
        }
        finally { // always execute this block if there is exception or not.
            System.out.println("program end");
        }
    }


}
