package StreamsApi.ComplexDataStructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class FlatEntry {
    private String outerKey;
    private String innerKey;
    private int value;

    public FlatEntry(String outerKey, String innerKey, int value) {
        this.outerKey = outerKey;
        this.innerKey = innerKey;
        this.value = value;
    }

    public String getOuterKey() {
        return outerKey;
    }

    public String getInnerKey() {
        return innerKey;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("[%s -> %s = %d]", outerKey, innerKey, value);
    }
}

public class FlattenNestedMap {
    public static void main(String[] args) {
        Map<String, Map<String, Integer>> nestedMap = new HashMap<>();

        Map<String, Integer> usaCities = new HashMap<>();
        usaCities.put("New York", 8400000);
        usaCities.put("Los Angeles", 3900000);

        Map<String, Integer> indiaCities = new HashMap<>();
        indiaCities.put("Mumbai", 12500000);
        indiaCities.put("Delhi", 11000000);

        nestedMap.put("USA", usaCities);
        nestedMap.put("India", indiaCities);

        List<FlatEntry> flatEntries = nestedMap.entrySet().stream()
                .flatMap(outer -> outer.getValue().entrySet().stream()
                        .map(inner -> new FlatEntry(outer.getKey(), inner.getKey(), inner.getValue())))
                .collect(Collectors.toList());

        System.out.println("Nested Map Structure:");
        System.out.println(nestedMap);
        System.out.println("\nFlattened List of Entries:");
        flatEntries.forEach(System.out::println);
    }
}
