package com.ipap.springboot3security.service;

import com.ipap.springboot3security.dto.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> products;

    @PostConstruct
    public void loadProductsFromDB() {
        products = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .id(i)
                        .name("product" + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextDouble(1000))
                        .build()
                ).collect(Collectors.toList());
    }


    public List<Product> getAllProducts() {
        return this.products;
    }


    public Product getProductById(int id) {
        return products.stream()
                .filter(p -> p.id() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Product " + id + " not found"));
    }
}
