package com.ecommerce.agroproducts.controller;

import com.ecommerce.agroproducts.service.UsersService;
import com.ecommerce.agroproducts.utils.requests.UsersRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class Users {

    private final UsersService usersService;

    @Autowired
    public Users(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(value = "add-user")
    public ResponseEntity<ApiResponse> addUser(@RequestBody UsersRequest usersRequest) {
        return usersService.addUsers(usersRequest);
    }

    @GetMapping(value = "get-users")
    public ResponseEntity<ApiResponse> getUser() {
        return usersService.getUsers();
    }


}
