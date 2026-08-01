package OOPs.Inheritance;


// Multilevel Inheritance: A --> B ---> C, C have all the property of A, and B and its own.

public class MultiLevelMain {
    public static void main(String[] args) {
        MultiLevelThree obj = new MultiLevelThree();
        obj.print();
        obj.display();
        obj.output();
    }
}
