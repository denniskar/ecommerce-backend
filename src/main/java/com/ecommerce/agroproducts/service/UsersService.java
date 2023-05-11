package com.ecommerce.agroproducts.service;

import com.ecommerce.agroproducts.utils.databind.UserDao;
import com.ecommerce.agroproducts.utils.requests.UsersRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UsersService {
    //ResponseEntity<ApiResponse> addUsers(UsersRequest usersRequest);
    Optional<UserDao> getUserByUsername(String username);
    ResponseEntity<ApiResponse> getUsers();
    ResponseEntity<ApiResponse> createUsers(UsersRequest createUserRequest);
    ResponseEntity<ApiResponse> getRoles();
}
