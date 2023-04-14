package com.ecommerce.agroproducts.service;

import com.ecommerce.agroproducts.utils.requests.SellProductsRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface SoldProductService {
    ResponseEntity<ApiResponse> sellProducts(SellProductsRequest sellProductsRequest, HttpServletRequest request);

    ResponseEntity<ApiResponse> soldProduct(Long id, String startDate, String endDate);
}
