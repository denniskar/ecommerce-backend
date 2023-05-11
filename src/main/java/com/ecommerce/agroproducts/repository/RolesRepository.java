package com.ecommerce.agroproducts.repository;

import com.ecommerce.agroproducts.Entity.Roles;
import com.ecommerce.agroproducts.utils.databind.RoleDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {

    Optional<Roles> findByRole(String role);
    List<RoleDao> findBy();
}
