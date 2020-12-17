package com.evginii.boot.springbootproject.service;

import com.evginii.boot.springbootproject.model.Product;
import com.evginii.boot.springbootproject.repository.ProductInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductInMemoryRepository productInMemoryRepository;

    public Optional<Product> findByID(Long id) {
        return productInMemoryRepository.findByID(id);
    }
    public List<Product> getAllProducts() {
        return productInMemoryRepository.getAllProducts();
    }
    public List<Product> getAllProducts(BigDecimal minCost, BigDecimal maxCost) {
        List<Product> out = getAllProducts();
        if (minCost != null) {
            out = out.stream().filter(p -> p.getCost().compareTo(minCost) > 0).collect(Collectors.toList());
        }
        if (maxCost != null) {
            out = out.stream().filter(p -> p.getCost().compareTo(maxCost) < 0).collect(Collectors.toList());
        }
        return out;
    }

    public Product saveOrUpdate(Product product){
        return productInMemoryRepository.saveOrUpdate(product);
    }
    public void removeById(Long id){
        productInMemoryRepository.removeById(id);
    }
}
