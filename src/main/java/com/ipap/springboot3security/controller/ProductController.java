package com.ipap.springboot3security.controller;

import com.ipap.springboot3security.dto.Product;
import com.ipap.springboot3security.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome, this endpoint is not secured");
    }

    // Only admin should access this endpoint
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") // Method level authorization
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Only user should access this endpoint
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')") // Method level authorization
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

}
