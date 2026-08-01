package OOPs.Inheritance;

// Hierarchical inheritance: One parent have multiple child, and they are extending their properties.

public class HierarchicalMain {
    public static void main(String[] args) {
        HierarchicalChild1 one = new HierarchicalChild1();
        System.out.println("== Calling Method with first Child Object ==");
        one.display();
        one.print();;

        System.out.println("== Calling methods with second child Object ==");
        HierarchicalChild2 second = new HierarchicalChild2();
        second.display();
        second.print();
    }
}
