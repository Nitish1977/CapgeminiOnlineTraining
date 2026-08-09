package StreamsApi.AdvancedMapping;

import java.util.Arrays;
import java.util.List;

public class LongestStringLength {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Apple", "Banana", "Dragonfruit", "Kiwi", "Strawberry");

        int maxLength = words.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        System.out.println("Words list: " + words);
        System.out.println("Length of longest string: " + maxLength);
    }
}
