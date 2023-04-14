package com.ecommerce.agroproducts.service.serviceImp;

import com.ecommerce.agroproducts.service.UsersService;
import com.ecommerce.agroproducts.utils.requests.UsersRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl  implements UsersService {

    @Override
    public ResponseEntity<ApiResponse> addUsers(UsersRequest usersRequest) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> getUsers() {
        return null;
    }
}
