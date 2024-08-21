package org.example.deduplication;

import java.util.*;

public class Main {



    public static void main(String[] args) {
        Product product1 = new Product(1, "Laptop", "Dell Laptop");
        Product product2 = new Product(2, "Mouse", "Acer Mouse");
        Product product3 = new Product(3, "Keyboard", "Acer KeyBoard");
        Product product4 = new Product(4, "Monitor", "Benq Monitor");

        // Sample Orders
        List<Order> orders = Arrays.asList(
                new Order(1, "Bangalore" , product1),
                new Order(1, "Ahmedabad" , product2),
                new Order(2, "Indore", product2),
                new Order(2, "Mumbai", product4),
                new Order(3, "Delhi", product3),
                new Order(4, "Surat", product4)
        );



        Map<Order, List<Product>> orderMap = new HashMap<>();

        for(Order order : orders) {
            Product product = order.getProduct();

            // If the orderId is already in the map, add the product to the list
            if (orderMap.containsKey(order)) {
                orderMap.get(order).add(product);
            } else {
                // Otherwise, create a new list and add the product
                List<Product> productList = new ArrayList<>();
                productList.add(product);
                orderMap.put(order, productList);
            }
        }

        for(Map.Entry<Order, List<Product>> entry : orderMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }
}


