package StreamsApi.ComplexDataStructure.FlatMapOperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UniqueWordsFromSentences {
    public static void main(String[] args) {
        List<String> sentences = Arrays.asList(
            "Java is a powerful programming language",
            "Streams in Java make collection processing elegant",
            "Functional programming with Java streams is powerful and clean"
        );

        List<String> uniqueWords = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split("\\s+")))
                .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                .filter(word -> !word.isEmpty())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Original Sentences: ");
        sentences.forEach(s -> System.out.println(" - " + s));
        System.out.println("\nUnique Words (alphabetical order):");
        System.out.println(uniqueWords);
    }
}
