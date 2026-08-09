package StreamsApi.Questions;

import org.w3c.dom.ls.LSException;

import javax.print.DocFlavor;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Question2 {
    public static void main(String[] args) {
        //Intermediate Level

        // find the sum of all numbers in a list:


        List<Integer> nums = Arrays.asList(10,60,3,20,20,30,55,89,40,10,23,65);

        long sum = nums.stream()
                .reduce(0, (a,b)->a+b);

        System.out.println("Sum of all elements : "+ sum);

        // avg of the values

        OptionalDouble avg = nums.stream()
                .mapToInt(Integer::intValue)
                .average();

        System.out.println("Average of values: "+ avg.getAsDouble());


        // find the products of all elements

        long product =
                nums.stream()
                        .reduce(1,(a,b)->a*b);
        System.out.println("Products of all elements: "+ product);


        // square of the elements

        List<Integer> squareList= nums.stream()
                .map(n->n*n).toList();
        System.out.println("Square of a list : "+squareList);

        //filter the names with more than 4 char

        List<String> names = Arrays.asList("Nitish","Mehul","Mohan","Mani","Msx");

        List<String> name4 =
                names
                        .stream()
                        .filter(n->n.length()>4).toList();

        System.out.println("Names with more than 4 character: "+name4);


        // find the second highest value

       int secondHighest =
                nums.stream()
                        .sorted(Comparator.reverseOrder())
                        .skip(1)
                        .findFirst().get();


        System.out.println("Second highest element: "+secondHighest);


        // find duplicate elements in a list;

        List<Integer> duplicateElement =
                nums.stream()
                        .distinct()
                        .filter(n->Collections.frequency(nums, n)>1)
                        .collect(Collectors.toList());

        System.out.println("Duplicate Element: "+duplicateElement);

        // remove duplicate element from a list
        List<Integer> uniqueElement =
                nums.stream()
                        .distinct()
                        .toList();

        System.out.println("Unique element or removed duplicate elements : "+ uniqueElement );

        //Group String by their Length
        System.out.println("===List With grouped Length===");
        Map<Integer, List<String>> groupedElement =
                names
                        .stream()
                        .collect(Collectors.groupingBy(String::length));
        groupedElement.forEach((length, group)->System.out.println("Length: "+length+" : "+group));


        // count the frequency of each element
        System.out.println("===Frequency of each elements===");

        Map<Integer, Long> freqMap =
                nums.stream()
                        .collect(Collectors.groupingBy(n->n, Collectors.counting()));

        freqMap.forEach((num, count)-> System.out.println(num + " Occurs "+count+" times"));


        // join all string comma separator

        String result = names.stream()
                .collect(Collectors.joining(", "));

        System.out.println("Joined String: "+result);

        // skip 3 elements and prints rest

        System.out.println("===Skip 3 elements and prints rest===");

        nums
                .stream()
                .skip(3).forEach(System.out::println);


        // limit to first 5 elements and print

        System.out.println("====Limit first 5 elements===");
        nums
                .stream()
                .limit(5)
                .forEach(System.out::println);

        System.out.println("=== Partition list into odd even===");

        Map<Boolean, List<Integer>> partitioned =
                nums.stream()
                        .collect(Collectors.partitioningBy(n->n%2 ==0));
        System.out.println("Even Numbers: "+partitioned.get(true));
        System.out.println("Odd Numbers: "+partitioned.get(false));

        // find the longest string from the list

        String longest =
                names.stream()
                        .max(Comparator.comparingInt(String::length)).get();

        System.out.println("Longest String : "+longest);


        //find the shortest string
        String shortest =
                names.stream()
                        .min(Comparator.comparing(String::length)).get();

        System.out.println("Shortest String : "+ shortest);

        // flatten a list of list using flatMap
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8)
        );

        // Flatten into a single list
        List<Integer> flattened = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(flattened);






    }
}
