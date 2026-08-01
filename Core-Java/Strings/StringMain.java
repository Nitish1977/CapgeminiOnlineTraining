package Strings;


// sequence of character
// Immutable in nature
// used to store text

public class StringMain {

    public static void main(String[] args) {
        // ways to declare string
        String str = "Capgemini"; // it will store string in string Constant pool
        // If the same value already exists, Java reuses the existing object
        System.out.println(str);


        String str2 = new String("Online"); // creates a new Object in heap memeory
        // does not use the existing literal object
        System.out.println(str2);


        // ==
        String name1 = "Nitish";
        String name2 = "Nitish";
        System.out.println(name1 == name2); // true because it checks reference both are pointing to the same string.

        String name3 = new String("JAVA");
        String name4 = new String("JAVA");

        System.out.println(name3 == name4); // return false: because both create different object and both have different memory reference.

        // so to compare the content we use .equals() method

        System.out.println(name1.equals(name2));
        System.out.println(name3.equals(name4));


        // Important String methods:
        System.out.println("=== Important String Methods ===");
        System.out.println(name1.length());
        System.out.println(name1.charAt(2));
        System.out.println(str.toUpperCase());
        System.out.println(str.toLowerCase());
        System.out.println(str.substring(2,4));


        //StringBuilder
        // Mutable String but not thread Safe

        //Creation
        StringBuilder sb = new StringBuilder("Java");
        sb.append(" Programming");
        System.out.println(sb);

        // StringBuffer
        //also Mutable but thread Safe
        StringBuffer sb1 = new StringBuffer("Java");
        sb1.append(" Full-Stack");
        System.out.println(sb1);






    }
}
