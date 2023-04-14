package com.ecommerce.agroproducts.repository;

import com.ecommerce.agroproducts.Entity.AssignedProducts;
import com.ecommerce.agroproducts.Entity.Products;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductAssignRepo extends JpaRepository<AssignedProducts,Long> {
    Optional<AssignedProducts> findByProducts(Products products);

}
