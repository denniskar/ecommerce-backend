package com.ecommerce.agroproducts.service.serviceImp;

import com.ecommerce.agroproducts.Entity.AssignedProducts;
import com.ecommerce.agroproducts.Entity.Products;
import com.ecommerce.agroproducts.Entity.SoldProducts;
import com.ecommerce.agroproducts.Entity.Users;
import com.ecommerce.agroproducts.repository.ProductAssignRepo;
import com.ecommerce.agroproducts.repository.ProductsRepo;
import com.ecommerce.agroproducts.repository.SoldProductsRepository;
import com.ecommerce.agroproducts.repository.UsersRepository;
import com.ecommerce.agroproducts.service.SoldProductService;
import com.ecommerce.agroproducts.utils.requests.SellProductsRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Service
public class SoldProductsServiceImp implements SoldProductService {
    private final ProductsRepo productsRepo;
    private final ProductAssignRepo productAssignRepo;
    private final UsersRepository usersRepository;
    private final SoldProductsRepository soldProductsRepository;

    public SoldProductsServiceImp(ProductsRepo productsRepo, ProductAssignRepo productAssignRepo, UsersRepository usersRepository, SoldProductsRepository soldProductsRepository) {
        this.productsRepo = productsRepo;
        this.productAssignRepo = productAssignRepo;
        this.usersRepository = usersRepository;
        this.soldProductsRepository = soldProductsRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiResponse> sellProducts(SellProductsRequest products, HttpServletRequest request) {
        Optional<Products> product = productsRepo.findById(products.getProductId());
        if (!product.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "product does not exist"));
        }
        if (product.get().getMaximumPrice() < products.getSellingPrice()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "The selling  price is higher the allowed price"));
        }
        Optional<Users> users = usersRepository.findById(products.getUserId());
        if (!users.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "user does not exist"));
        }
        /// sell a product
        Optional<AssignedProducts> assignedProducts = productAssignRepo.findByProducts(product.get());
        if (!assignedProducts.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "This product is not available in your store"));
        }
        if (assignedProducts.get().getQuantity() < products.getQuantity()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "quantity is more than quantity in your store"));
        }

        try {

            // profit;
            products.setProfit((products.getSellingPrice() - product.get().getProductPrice()) * products.getQuantity());
            SoldProducts soldProducts = soldProductsRepository.save(SoldProducts.of(products, assignedProducts.get(), users.get()));
            assignedProducts.get().setQuantity(assignedProducts.get().getQuantity() - soldProducts.getQuantity());
            productAssignRepo.save(assignedProducts.get());
        } catch (Exception E) {
            E.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1, "quantity is more than quantity in your store"));
        }

        return ResponseEntity.ok(ApiResponse.response(0, "success"));
    }

    @Override
    public ResponseEntity<ApiResponse> soldProduct(Long id, String startDate, String endDate) {
            if(endDate!=null && startDate==null ){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.response(1,"please supply the start date"));
            }
            return  null;

    }
}
