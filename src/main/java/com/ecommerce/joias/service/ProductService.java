package com.ecommerce.joias.service;

import com.ecommerce.joias.dto.CreateProductDto;
import com.ecommerce.joias.dto.ProductResponseDto;
import com.ecommerce.joias.dto.ProductVariantResponseDto;
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

    public ProductResponseDto createProduct(CreateProductDto createProductDto){
        var category = categoryRepository.findById(createProductDto.categoryId()).orElseThrow(() -> new RuntimeException("Categoria correspondente ao id fornecido não encontrada."));

        // DTO -> ENTITY
        var productEntity = new Product();
        productEntity.setName(createProductDto.name());
        productEntity.setCategory(category);
        productEntity.setDescription(createProductDto.description());
        productEntity.setMaterial(createProductDto.material());

        var productSaved = productRepository.save(productEntity);

        var categoryInfo = new ProductResponseDto.CategoryInfo(
                category.getCategoryId(),
                category.getName()
        );

        return new ProductResponseDto(
                productSaved.getProductId(),
                productSaved.getName(),
                productSaved.getDescription(),
                productSaved.getMaterial(),
                categoryInfo,
                java.util.List.of()
        );
    }

    public ProductResponseDto getProductById(Integer productId){
        var product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        var productVariantsDto = product.getVariants().stream()
                .map(variant -> new ProductVariantResponseDto(
                        variant.getProductVariantId(),
                        variant.getSize(),
                        variant.getSku(),
                        variant.getPrice(),
                        variant.getStockQuantity(),
                        variant.getWeightGrams()
                )).toList();

        var categoryInfo = new ProductResponseDto.CategoryInfo(
                product.getCategory().getCategoryId(),
                product.getCategory().getName()
        );

        return new ProductResponseDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getMaterial(),
                categoryInfo,
                productVariantsDto
        );
    }

    public void deleteProductById(Integer productId){
        productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produto com esse id não encontrado"));

        productRepository.deleteById(productId);
    }
}
