package com.ecommerce.agroproducts.service.serviceImp;

import com.ecommerce.agroproducts.Entity.AssignedProducts;
import com.ecommerce.agroproducts.Entity.Products;
import com.ecommerce.agroproducts.Entity.Users;
import com.ecommerce.agroproducts.repository.ProductAssignRepo;
import com.ecommerce.agroproducts.repository.ProductsRepo;
import com.ecommerce.agroproducts.repository.UsersRepository;
import com.ecommerce.agroproducts.service.AssignedProductService;
import com.ecommerce.agroproducts.utils.requests.AssignedProductRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductAssignmentServiceImpl implements AssignedProductService {
    private  final ProductAssignRepo productAssignRepo;
    private final ProductsRepo productsRepo;

    private  final UsersRepository usersRepository;

@Autowired
    public ProductAssignmentServiceImpl(ProductAssignRepo productAssignRepo, ProductsRepo productsRepo, UsersRepository usersRepository) {
        this.productAssignRepo = productAssignRepo;
    this.productsRepo = productsRepo;
    this.usersRepository = usersRepository;
}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> assignProducts(AssignedProductRequest request){

        Optional<Products>products=productsRepo.findById(request.getProductId());
        if(!products.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1,"product does not exist ,consider adding it first "));
        }
      Optional<Users>users=usersRepository.findById(request.getUserId());
        if(!users.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1,"user does not exist "));
        }

       if( products.get().getUnitsAvailable()< request.getQuantity()){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1,"the units  assigned is more than units Available"));
       }
          try {
              AssignedProducts products1= productAssignRepo.save(AssignedProducts.of(request,users.get(),products.get()));
              products.get().setUnitsAvailable(products.get().getUnitsAvailable()- products1.getQuantity());
              productsRepo.save(products.get());
              return  ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.response(0,"product Assigned Successfully"));
          }catch (Exception e){
              e.printStackTrace();
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1,"Error Occurred "));
          }
    }

    @Override
    public ResponseEntity<ApiResponse> getAssignedProducts(Long userId, Long productId) {
        return null;
    }
}
