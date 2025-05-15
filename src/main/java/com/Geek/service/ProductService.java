package com.Geek.service;

import com.Geek.dto.request.ProductRequestDto;
import com.Geek.model.Product;
import com.Geek.model.Store;
import com.Geek.repository.AddressRepository;
import com.Geek.repository.ProductRepository;
import com.Geek.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    public ProductService(ProductRepository productRepository,
                          StoreRepository storeRepository,
                          AddressRepository addressRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    @Transactional
    public Product createProduct(ProductRequestDto productRequest) {

        UUID storeId = UUID.fromString(productRequest.getStoreId());
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + storeId));
        // Create Product
        Product product = new Product();
        product.setStore(store);
        product.setName(productRequest.getName());
        product.setCurrentPrice(productRequest.getCurrentPrice());

        return productRepository.save(product);
    }
}