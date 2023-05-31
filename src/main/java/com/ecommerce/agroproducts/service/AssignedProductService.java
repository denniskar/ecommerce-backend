package com.ecommerce.agroproducts.service;

import com.ecommerce.agroproducts.utils.requests.AssignedProductRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AssignedProductService {
    ResponseEntity<ApiResponse> assignProducts(AssignedProductRequest assignedProductRequest);
    ResponseEntity<ApiResponse> getAssignedProducts(Long userId, Long productId);
}
