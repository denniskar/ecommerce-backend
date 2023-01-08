package com.ecommerce.agroproducts.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AssignedProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  Double quantity;
    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinTable(name="productsAssigned",
    joinColumns = @JoinColumn(name="assignedProductId",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="productId",referencedColumnName = "id"))
    private Set<Products> products;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private  Users users;

}
