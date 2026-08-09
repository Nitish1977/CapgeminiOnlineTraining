package StreamsApi.ComplexDataStructure.FlatMapOperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlattenListOfArrays {
    public static void main(String[] args) {
        List<String[]> listOfArrays = Arrays.asList(
            new String[]{"Java", "Python"},
            new String[]{"C++", "JavaScript", "Go"},
            new String[]{"Rust", "Kotlin"}
        );

        List<String> flatList = listOfArrays.stream()
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println("Flattened List of Array Strings: " + flatList);
    }
}
