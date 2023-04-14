package com.ecommerce.agroproducts.controller;

import com.ecommerce.agroproducts.service.SoldProductService;
import com.ecommerce.agroproducts.utils.requests.SellProductsRequest;
import com.ecommerce.agroproducts.utils.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/sell/")
@RestController
public class SoldProduct {
    private  final SoldProductService productService;
    @Autowired
    public SoldProduct(SoldProductService productService) {
        this.productService = productService;
    }

    @PostMapping("sell-products")
    public ResponseEntity<ApiResponse> sellProducts(@RequestBody  SellProductsRequest sellProductsRequest, HttpServletRequest request){
       return productService.sellProducts(sellProductsRequest,request);
    }

    @GetMapping("sold-products")
    public  ResponseEntity<ApiResponse>soldProducts(@RequestParam(name = "id")Long id,@RequestParam(name = "startDate")String startDate,@RequestParam(name = "endDate") String endDate ){
        return  productService.soldProduct(id,startDate,endDate);
    }


}
