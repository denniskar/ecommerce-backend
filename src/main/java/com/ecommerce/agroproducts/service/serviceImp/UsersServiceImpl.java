package com.ecommerce.agroproducts.service.serviceImp;

import com.ecommerce.agroproducts.service.UsersService;
import com.ecommerce.agroproducts.utils.databind.UserDao;
import com.ecommerce.agroproducts.utils.requests.UsersRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl  implements UsersService {

    @Override
    public ResponseEntity<ApiResponse> addUsers(UsersRequest usersRequest) {
        return null;
    }

    @Override
    public Optional<UserDao> getUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<ApiResponse> getUsers() {
        return null;
    }
}
