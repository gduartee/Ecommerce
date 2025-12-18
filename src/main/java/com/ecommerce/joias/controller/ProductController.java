package com.ecommerce.joias.controller;

import com.ecommerce.joias.dto.CreateProductDto;
import com.ecommerce.joias.dto.ProductResponseDto;
import com.ecommerce.joias.entity.Product;
import com.ecommerce.joias.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid CreateProductDto createProductDto){
        var product = productService.createProduct(createProductDto);

        var location = URI.create("/products/" + product.productId());

        return ResponseEntity.created(location).body(product);
    }
}
