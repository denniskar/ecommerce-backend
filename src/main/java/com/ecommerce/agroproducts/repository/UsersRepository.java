package com.ecommerce.agroproducts.repository;

import com.ecommerce.agroproducts.Entity.Users;
import com.ecommerce.agroproducts.utils.databind.UserDao;
import com.ecommerce.agroproducts.utils.databind.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    public Optional<Users> findByUsername(String username);

    List<UserDao> findBy();

    Optional<Users> findByPhoneNumber(String phoneNumber);

    Optional<Users> findByEmail(String phoneNumber);
    @Transactional
    @Query(value = "SELECT new com.ecommerce.agroproducts.utils.databind.UserDto(t.id,t.username,t.firstName,t.lastName)from Users t where  t.username=?1")
    UserDto findFirstByUsername(String loans);
}
