package com.ecommerce.agroproducts.service.serviceImp;
import com.ecommerce.agroproducts.Entity.Product;
import com.ecommerce.agroproducts.Entity.Products;
import com.ecommerce.agroproducts.Entity.Users;
import com.ecommerce.agroproducts.repository.ProductAssignRepo;
import com.ecommerce.agroproducts.repository.ProductsRepo;
import com.ecommerce.agroproducts.repository.UsersRepository;
import com.ecommerce.agroproducts.utils.requests.SellProductsRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class SoldProductsServiceImp {
     private  final ProductsRepo productsRepo;
     private  final ProductAssignRepo productAssignRepo;
     private final UsersRepository usersRepository;
    public SoldProductsServiceImp(ProductsRepo productsRepo, ProductAssignRepo productAssignRepo, UsersRepository usersRepository) {
        this.productsRepo = productsRepo;
        this.productAssignRepo = productAssignRepo;
        this.usersRepository = usersRepository;
    }
@Transactional(rollbackFor = Exception.class)
    ResponseEntity<ApiResponse> sellProducts(SellProductsRequest products){
    Optional<Products>product=productsRepo.findById(products.getProductId());
    if(!product.isPresent()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1,"product does not exist"));
    }
   Optional<Users>users=usersRepository.findById(products.getUserId());
    if(!users.isPresent()){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1,"user does not exist"));
    }
    return  null;
}}
