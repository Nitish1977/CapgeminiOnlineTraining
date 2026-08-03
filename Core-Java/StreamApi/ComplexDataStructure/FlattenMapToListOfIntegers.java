package Streams.ComplexDataStructures;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlattenMapToListOfIntegers {
    public static void main(String[] args) {
        Map<String, List<Integer>> mapOfLists = new HashMap<>();
        mapOfLists.put("Set A", Arrays.asList(10, 20, 30));
        mapOfLists.put("Set B", Arrays.asList(40, 50));
        mapOfLists.put("Set C", Arrays.asList(60, 70, 80, 90));

        List<Integer> allIntegers = mapOfLists.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println("Input Map: " + mapOfLists);
        System.out.println("Flattened List of All Integers: " + allIntegers);
    }
}
