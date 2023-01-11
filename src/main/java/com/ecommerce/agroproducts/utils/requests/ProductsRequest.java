package com.ecommerce.agroproducts.utils.requests;

import com.ecommerce.agroproducts.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsRequest implements Serializable {
    private Long id;
    private  String productName;
    private  String manufacturingCompany;
    private  String ProductSize;
    private  String unitsAvailable;
    private  Double productPrice;
    private Double sellingPrice;
    private String productCode;
    private Double maximumPrice;
    private LocalDateTime dateAdded;

    private  LocalDateTime expiryDate;

    private Product ProductId;
}
