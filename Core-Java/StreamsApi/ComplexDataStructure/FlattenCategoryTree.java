package StreamsApi.ComplexDataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Category {
    private String name;
    private List<Category> subCategories;

    public Category(String name, List<Category> subCategories) {
        this.name = name;
        this.subCategories = subCategories != null ? subCategories : new ArrayList<>();
    }

    public Category(String name) {
        this(name, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    // Helper method to recursively stream self and all nested subcategories
    public Stream<Category> flatten() {
        return Stream.concat(
            Stream.of(this),
            subCategories.stream().flatMap(Category::flatten)
        );
    }

    @Override
    public String toString() {
        return name;
    }
}

public class FlattenCategoryTree {
    public static void main(String[] args) {
        // Build Category Tree Hierarchy:
        // Electronics
        // ├── Mobiles
        // │   ├── Smartphones
        // │   └── Feature Phones
        // └── Laptops
        //     ├── Gaming Laptops
        //     └── Ultrabooks

        Category smartphones = new Category("Smartphones");
        Category featurePhones = new Category("Feature Phones");
        Category mobiles = new Category("Mobiles", Arrays.asList(smartphones, featurePhones));

        Category gamingLaptops = new Category("Gaming Laptops");
        Category ultrabooks = new Category("Ultrabooks");
        Category laptops = new Category("Laptops", Arrays.asList(gamingLaptops, ultrabooks));

        Category electronics = new Category("Electronics", Arrays.asList(mobiles, laptops));

        List<Category> rootCategories = Arrays.asList(electronics);

        // Flatten the entire category tree using Stream API
        List<String> flatCategoryNames = rootCategories.stream()
                .flatMap(Category::flatten)
                .map(Category::getName)
                .collect(Collectors.toList());

        System.out.println("Flattened Tree Categories List:");
        System.out.println(flatCategoryNames);
    }
}
