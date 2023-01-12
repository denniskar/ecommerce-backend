package com.ecommerce.agroproducts.Entity;

import com.ecommerce.agroproducts.utils.requests.AssignedProductRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class AssignedProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  Double quantity;
    @JoinColumn(name = "productId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, optional = false)
    private  Products products;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private  Users users;

    public static AssignedProducts of(AssignedProductRequest request, Users users, Products products) {
        AssignedProducts assignedProducts=new AssignedProducts();
        assignedProducts.setProducts(products);
        assignedProducts.setUsers(users);
        return assignedProducts;
    }
}
