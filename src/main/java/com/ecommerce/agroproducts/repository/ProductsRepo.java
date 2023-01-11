package com.ecommerce.agroproducts.repository;

import com.ecommerce.agroproducts.Entity.Product;
import com.ecommerce.agroproducts.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepo  extends JpaRepository<Products,Long> {
    Optional<Products> findByProductCode(String productCode);
}
