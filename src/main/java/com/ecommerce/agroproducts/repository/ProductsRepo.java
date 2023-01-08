package com.ecommerce.agroproducts.repository;

import com.ecommerce.agroproducts.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo  extends JpaRepository<Product,Long> {
}
