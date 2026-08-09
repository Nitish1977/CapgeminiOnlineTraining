package StreamsApi.SortingComplex;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStringsByLengthThenAlpha {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Banana", "Apple", "Fig", "Date", "Cherry", "Kiwi", "Bat", "Cat");

        List<String> sortedWords = words.stream()
                .sorted(Comparator.comparing(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());

        System.out.println("Original Words: " + words);
        System.out.println("Sorted (by length, then alphabetically): " + sortedWords);
    }
}
