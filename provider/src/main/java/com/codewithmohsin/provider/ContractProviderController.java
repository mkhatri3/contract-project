package com.codewithmohsin.provider;

import com.provider.api.ProductsApi;
import com.provider.model.Product;
import com.provider.model.ProductCreate;
import com.provider.model.ProductUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.*;

@RestController
public class ContractProviderController implements ProductsApi {

    Map<UUID, Product> products = new HashMap<>();

    //Get all products
    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(new ArrayList<>(products.values()));
    }

    //Get a specific product
    @Override
    public ResponseEntity<Product> getProductById(UUID id) {
        Product product = products.get(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Create a product
    @Override
    public ResponseEntity<Product> addProduct(ProductCreate productCreate) {
        UUID id = UUID.randomUUID();
        Product newProduct = new Product()
                .id(id)
                .name(productCreate.getName())
                .price(productCreate.getPrice())
                .description(productCreate.getDescription())
                .currency(productCreate.getCurrency())
                .createdAt(OffsetDateTime.now());
        products.put(id, newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    //Update a product
    @Override
    public ResponseEntity<Product> updateProduct(UUID productId, ProductUpdate productUpdate) {
        Product existingProduct = products.get(productId);
        if (existingProduct != null) {
            existingProduct
                    .name(productUpdate.getName())
                    .price(productUpdate.getPrice())
                    .description(productUpdate.getDescription())
                    .currency(productUpdate.getCurrency())
                    .updatedAt(OffsetDateTime.now());
            return ResponseEntity.ok(existingProduct);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Delete a product
    @Override
    public ResponseEntity<Void> deleteProduct(UUID productId) {
        if  (products.containsKey(productId)) {
            products.remove(productId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else return ResponseEntity.notFound().build();
    }
}
