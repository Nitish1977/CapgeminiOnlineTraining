package StreamsApi.SortingComplex;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByAbsoluteValue {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(-15, 3, -2, 42, -9, 0, 7, -50, 12);

        List<Integer> sortedByAbs = numbers.stream()
                .sorted(Comparator.comparingInt(Math::abs))
                .collect(Collectors.toList());

        System.out.println("Original Numbers: " + numbers);
        System.out.println("Sorted by Absolute Value: " + sortedByAbs);
    }
}
