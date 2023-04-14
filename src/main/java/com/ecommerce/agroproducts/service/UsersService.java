package com.ecommerce.agroproducts.service;

import com.ecommerce.agroproducts.utils.requests.UsersRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface UsersService {
    ResponseEntity<ApiResponse> addUsers(UsersRequest usersRequest);

    ResponseEntity<ApiResponse> getUsers();
}
