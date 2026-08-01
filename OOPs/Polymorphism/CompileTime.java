package OOPs.Polymorphism;

public class CompileTime {

    // it is also called static polymorphism
    // implement with method overloading.
    // Overloading: same methods with different parameters

    public int sum(int a , int b)
    {
        return a+b;
    }


    //overloading
    public int sum(int a, int b, int c){
        return a+b+c;
    }
}
