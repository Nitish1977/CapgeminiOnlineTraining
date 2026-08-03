package Streams.AdvancedMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringLengthMap {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java", "Stream", "Lambda", "Functional", "API");

        Map<String, Integer> wordLengthMap = words.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        String::length,
                        (existing, replacement) -> existing
                ));

        System.out.println("Original String List: " + words);
        System.out.println("String -> Length Map: " + wordLengthMap);
    }
}
