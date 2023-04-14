package com.ecommerce.agroproducts.controller;

import com.ecommerce.agroproducts.service.ProductService;
import com.ecommerce.agroproducts.utils.requests.Webproducts;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class Products {
       private  final  ProductService productService;
@Autowired
    public Products(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "add-web-product")
    public ResponseEntity<ApiResponse> addProducts(@RequestBody Webproducts webproducts){
              return productService.addProducts(webproducts);
    }

   @GetMapping("/assigned-products")
    public  ResponseEntity<ApiResponse>getAssignedProducts(){
    return null;
   }


}
