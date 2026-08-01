package OOPs.Polymorphism;

public class CompileTimeMain {

    public static void main(String[] args) {
        CompileTime obj = new CompileTime();
        System.out.println("Sum: "+obj.sum(12,44));
        System.out.println("Sum: "+obj.sum(12,23,44));
    }
}
