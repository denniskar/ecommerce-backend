package com.ecommerce.agroproducts.service.serviceImp;

import com.ecommerce.agroproducts.service.ProductService;
import com.ecommerce.agroproducts.utils.requests.Webproducts;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public ResponseEntity<ApiResponse> addProducts(Webproducts webproducts) {
        return null;
    }
}
