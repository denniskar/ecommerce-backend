package com.ecommerce.agroproducts.service;

import com.ecommerce.agroproducts.utils.requests.Webproducts;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<ApiResponse> addProducts(Webproducts webproducts);
}
