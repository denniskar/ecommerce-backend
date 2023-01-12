package com.ecommerce.agroproducts.utils.requests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellProductsRequest implements Serializable {
    private  String sellingPrice;
    private  Double quantity;
    private Double profit;
    private Long userId;
    private Long productId;
}
