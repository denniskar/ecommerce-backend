package com.ecommerce.agroproducts.repository;

import com.ecommerce.agroproducts.Entity.SoldProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoldProductsRepository extends JpaRepository<SoldProducts,Long> {
}
