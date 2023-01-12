package com.ecommerce.agroproducts.repository;

import com.ecommerce.agroproducts.Entity.AssignedProducts;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAssignRepo extends JpaRepository<AssignedProducts,Long> {
}
