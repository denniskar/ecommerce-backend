package com.ecommerce.agroproducts.utils.requests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignedProductRequest implements Serializable {
    private Long id;
    private  Double quantity;
    private Long productId;
    private Long userId;
}
