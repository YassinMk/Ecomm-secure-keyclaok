package com.ecom.inventoryservice.web;

import com.ecom.inventoryservice.entities.Product;
import com.ecom.inventoryservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductRestController {
    private final ProductRepository productRepository;

    @GetMapping("/products")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> productList(){
        return productRepository.findAll();
    }
    @GetMapping("/products/{id}")
    //@PreAuthorize("hasAuthority('USER')")
    public Product productById(@PathVariable String id){
        return productRepository.findById(id).get();
    }
    @GetMapping("/auth")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }

}
