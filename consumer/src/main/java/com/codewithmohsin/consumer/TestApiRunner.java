package com.codewithmohsin.consumer;

import com.consumer.api.ProductsApi;
import com.consumer.model.Product;
import com.consumer.model.ProductCreate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestApiRunner implements CommandLineRunner {

    public void run(String... args) {
        ProductsApi api = new ProductsApi();
        api.getApiClient().setBasePath("http://localhost:8080/v1");

        try {
            ProductCreate productCreate = new ProductCreate()
                    .name("OpenAPI Test")
                    .description("This is a test API")
                    .price(10.99f)
                    .currency("USD");

            Product createProduct = api.addProduct(productCreate);
            System.out.println("Product Created: " + createProduct);

            List<Product> products = api.getAllProducts();
            System.out.println("All Products: ");
            products.forEach(p -> System.out.println(" - " + p.getName()));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
