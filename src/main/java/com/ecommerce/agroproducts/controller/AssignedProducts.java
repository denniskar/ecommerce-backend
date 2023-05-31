package com.ecommerce.agroproducts.controller;
import com.ecommerce.agroproducts.service.AssignedProductService;
import com.ecommerce.agroproducts.utils.requests.AssignedProductRequest;
import com.ecommerce.agroproducts.utils.requests.UsersRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products/")
public class AssignedProducts {
      private  final AssignedProductService assignedProductService;
    @Autowired
    public AssignedProducts(AssignedProductService assignedProductService) {
        this.assignedProductService = assignedProductService;
    }

    @PostMapping(value = "assign-products")
    public ResponseEntity<ApiResponse> assignProducts(@RequestBody AssignedProductRequest assignedProductRequest) {
        return assignedProductService.assignProducts(assignedProductRequest);
    }

    @GetMapping (value = "get-assign-products")
    public ResponseEntity<ApiResponse>getAssigned(@RequestParam(name = "userId")Long userId,@RequestParam(name = "productId") Long productId) {
        return assignedProductService.getAssignedProducts(userId,productId);
    }
}
