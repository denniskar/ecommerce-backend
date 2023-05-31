package com.ecommerce.agroproducts.repository;
import com.ecommerce.agroproducts.Entity.Users;
import com.ecommerce.agroproducts.utils.databind.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Repo extends JpaRepository<Users,Long> {

    Optional<UserDao> findByUsername(String username);
}
