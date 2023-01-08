package com.ecommerce.agroproducts.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String productName;
    private  String manufacturingCompany;
    private  String ProductSize;
    private  String unitsAvailable;
    private  Double productPrice;
    private Double sellingPrice;
    private Double maximumPrice;
    private LocalDateTime dateAdded;

    private  LocalDateTime expiryDate;
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, optional = false)
    private  ProductCategories productCategories;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<AssignedProducts> assignedProducts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "products", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SoldProducts> soldProducts;
}
