package StreamsApi.AdvancedMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

public class ProductNameToPriceMap {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1200.50),
            new Product("Smartphone", 800.00),
            new Product("Headphones", 150.75),
            new Product("Monitor", 300.25)
        );

        Map<String, Double> productPriceMap = products.stream()
                .collect(Collectors.toMap(
                        Product::getName,
                        Product::getPrice
                ));

        System.out.println("Product Name -> Price Map:");
        productPriceMap.forEach((name, price) -> System.out.println(name + " => $" + price));
    }
}
