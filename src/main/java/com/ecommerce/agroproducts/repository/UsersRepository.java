package com.ecommerce.agroproducts.repository;

import com.ecommerce.agroproducts.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
