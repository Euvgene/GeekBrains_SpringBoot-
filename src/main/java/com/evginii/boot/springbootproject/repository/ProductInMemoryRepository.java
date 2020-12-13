package com.evginii.boot.springbootproject.repository;

import com.evginii.boot.springbootproject.exceptions.ResourceNotFoundException;
import com.evginii.boot.springbootproject.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Data
@AllArgsConstructor
public class ProductInMemoryRepository {
    private List<Product> productList;

    @PostConstruct
    public void init(){
        productList = new ArrayList<>();
        productList.add(new Product(125896L,"Boots", new BigDecimal("999.99")));
        productList.add(new Product(122396L,"Hat", new BigDecimal("599.99")));
        productList.add(new Product(125456L,"T-shirt", new BigDecimal("699.99")));
        productList.add(new Product(123696L,"Glasses", new BigDecimal("1499.99")));
        productList.add(new Product(125815L,"Shoes", new BigDecimal("1999.99")));
    }

    public List<Product> getAllProducts(){
        return Collections.unmodifiableList(productList);
    }

    public Product saveOrUpdate(Product product){
        if (product.getId()!=null){
            for (int i = 0; i <productList.size() ; i++) {
                if (productList.get(i).getId().equals(product.getId())){
                    productList.set(i,product);
                    return product;
                }
            }
        }
        Long newId = productList.stream().mapToLong(Product::getId).max().orElseGet(()->0L)+1L;
        product.setId(newId);
        productList.add(product);
        return product;
    }

    public Optional<Product> findByID(Long id){
        return productList.stream().filter(p->p.getId().equals(id)).findFirst();
    }

    public void removeById(long id){
        productList.removeIf(p-> p.getId().equals(id));
    }
}
