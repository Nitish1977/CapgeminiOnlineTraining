package StreamsApi.FlatMapOperations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlattenMapOfLists {
    public static void main(String[] args) {
        Map<String, List<String>> categoryItemsMap = new HashMap<>();
        categoryItemsMap.put("Electronics", Arrays.asList("TV", "Radio", "Camera"));
        categoryItemsMap.put("Groceries", Arrays.asList("Milk", "Bread", "Eggs"));
        categoryItemsMap.put("Clothing", Arrays.asList("Shirt", "Jeans"));

        List<String> allItems = categoryItemsMap.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println("Original Map of Lists: " + categoryItemsMap);
        System.out.println("Flattened Single List: " + allItems);
    }
}
