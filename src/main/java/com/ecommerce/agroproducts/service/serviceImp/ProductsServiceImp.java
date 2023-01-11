package com.ecommerce.agroproducts.service.serviceImp;

import com.ecommerce.agroproducts.Entity.Products;
import com.ecommerce.agroproducts.repository.ProductsRepo;
import com.ecommerce.agroproducts.utils.requests.ProductsRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductsServiceImp {
    private  final ProductsRepo productsRepo;
@Autowired
    public ProductsServiceImp(ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

    ResponseEntity<ApiResponse> addProducts(ProductsRequest products){
        ApiResponse apiResponse =new ApiResponse();

        Optional<Products>product=productsRepo.findByProductCode(products.getProductCode());
   if(product.isPresent()){
       apiResponse.response(1,"This product already exists");
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
   }
   try {
       productsRepo.save(Products.valueOf(products));
       apiResponse.response(0,"product added successfully");
   }catch (Exception e){
       e.printStackTrace();
       apiResponse.response(1,"error Occurred");
       return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
   }


        return  ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    };
}
