package StreamsApi.Questions;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.*;

public class Questions {
    public static void main(String[] args) {
        //Beginner Level 1:
        //Print all even Number from a List:
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(4);
        nums.add(5);
        nums.add(6);
        nums.add(7);
        nums.add(3);

        System.out.println("ALl EVEN NUMBERS: ");
        List<Integer> even= nums.stream()
                .filter(n->n%2==0)
                .toList();

        System.out.println(even);

        //print all odd numbers;
        System.out.println("ODD NUMBERS ARE: ");
        List<Integer> odd = nums.stream()
                .filter(n->n%2!=0).toList();
        System.out.println(odd);


        //convert all string to uppercase

        List<String> names = new ArrayList<>();
        names.add("hari");
        names.add("Mehul");
        names.add("Nitish");
        names.add("Aditya");
        names.add("Mirz");
        names.add("Priya");


        List<String> upperCase = names.stream()
                .map(String::toUpperCase).toList();
        System.out.println("Names in UpperCase: ");
        System.out.println(upperCase);

        //convert the names in lowercase

        List<String> loweCase = names.stream()
                .map(String::toLowerCase).toList();
        System.out.println("Names are in LowerCase: "+loweCase);


        // find the first element that start with "A";
        Optional<String> firstElement = names
                .stream()
                .filter(name->name.startsWith("A"))
                .findFirst();

        System.out.println("First Name with A  : "+firstElement.get());


        //find any element ends with "Z"
        Optional<String> element = names
                .stream()
                .filter(name->name.endsWith("z"))
                .findAny();

        System.out.println("Any Element ends with Z : "+element.get());

        // count the elements which is grater than the given element;
        int i = 2;
       long count =
                nums.stream()
                        .filter(n->n>i)
                        .count();

        System.out.println("Count of number which is greater than "+i+" number: "+count);


        // check if any number is divisible by 5
        boolean isAvailable = nums
                .stream()
                .anyMatch(n->n%5==0);
        System.out.println("Check is any number is divisible by 5: "+isAvailable);

        //check if all elements are positive:

        boolean isAllPositive = nums.stream()
                .allMatch(n->n>0);
        System.out.println("All Elements are Positive or not: "+isAllPositive);

        //remove all null values from a list

        List<String>  subject = new ArrayList<>();
        subject.add(null);
        subject.add("Computer");
        subject.add("IT");
        subject.add(null);
        subject.add("Maths");

        List<String> newList = subject
                .stream()
                .filter(Objects::nonNull).toList();

        System.out.println("List without null value: "+newList);

        // Sort elements in ascending order

        List<Integer> sortAsc =
                nums.stream()
                        .sorted()
                        .toList();

        System.out.println("Sorted elements in Ascending order: "+sortAsc);

        // sort element in descending order
        List<Integer> sortdec =
                nums.stream()
                        .sorted(Comparator.reverseOrder())
                        .toList();

        System.out.println("Sorted elements in Descending order: "+sortdec);

        // Find the maximum value form a list

        Optional<Integer> maxVal =
                nums.stream()
                        .max(Integer::compare);

        System.out.println("Maximum Value from a list: "+maxVal.get());



    }
}
