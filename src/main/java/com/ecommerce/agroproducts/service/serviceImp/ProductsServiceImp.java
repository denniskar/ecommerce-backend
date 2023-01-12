package com.ecommerce.agroproducts.service.serviceImp;

import com.ecommerce.agroproducts.Entity.Products;
import com.ecommerce.agroproducts.repository.ProductsRepo;
import com.ecommerce.agroproducts.utils.requests.ProductsRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ProductsServiceImp {
    private  final ProductsRepo productsRepo;
@Autowired
    public ProductsServiceImp(ProductsRepo productsRepo) {
        this.productsRepo = productsRepo;
    }

    ResponseEntity<ApiResponse> addProducts(ProductsRequest products){
        Optional<Products>product=productsRepo.findByProductCode(products.getProductCode());
   if(product.isPresent()){
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1,"This product already exists"));
   }
   try {
       productsRepo.save(Products.valueOf(products));
   }catch (Exception e){
       e.printStackTrace();
       return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1,"  Error Occurred"));
   }
        return  ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.response(0,"product Added successfully"));
    };

ResponseEntity<Map<String,Object>>getProducts(){
    Map<String,Object>response=new HashMap<>();
  List<Products>products= productsRepo.findAll();
  response.put("status","0");
  response.put("message","success");
  response.put("data",products);

    return ResponseEntity.ok(response);
}
}



