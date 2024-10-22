package com.project.product_service.service;

import com.project.product_service.dto.ProductRequest;
import com.project.product_service.dto.ProductResponce;
import com.project.product_service.model.Product;
import com.project.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
/*
* this is better than Autowired
* bcs help to test
* with "mock object"
* */
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product  {} is saved",product.getId());
    }

    public List<ProductResponce> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponce).toList();
    }
    public ProductResponce mapToProductResponce(Product product){
        return ProductResponce.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
