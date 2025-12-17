package com.ecommerce.joias.service;

import com.ecommerce.joias.dto.CreateProductDto;
import com.ecommerce.joias.entity.Product;
import com.ecommerce.joias.repository.CategoryRepository;
import com.ecommerce.joias.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product createProduct(CreateProductDto createProductDto){
        var category = categoryRepository.findById(createProductDto.categoryId()).orElseThrow(() -> new RuntimeException("Categoria correspondente ao id fornecido não encontrada."));

        // DTO -> ENTITY
        var productEntity = new Product();
        productEntity.setName(createProductDto.name());
        productEntity.setCategory(category);
        productEntity.setDescription(createProductDto.description());
        productEntity.setMaterial(createProductDto.material());

        return productRepository.save(productEntity);
    }
}
