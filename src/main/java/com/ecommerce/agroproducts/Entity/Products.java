package com.ecommerce.agroproducts.Entity;

import com.ecommerce.agroproducts.utils.requests.ProductsRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "productName", nullable = false, length = 24)
    private  String productName;
    private  String manufacturingCompany;
    private  String ProductSize;
    private String productCode;
    private Double unitsAvailable;
    private  Double actualUnits;
    @Column(name = "buyingPrice", nullable = false, length = 5)
    private  Double productPrice;
    @Column(name = "sellingPrice", nullable = false, length = 5)
    private Double sellingPrice;
    @Column(name = "maximumPrice", nullable = false, length = 5)
    private Double maximumPrice;
    private LocalDateTime dateAdded;

    private  LocalDateTime expiryDate;
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, optional = false)
    private  ProductCategories productCategories;

    @OneToMany(mappedBy = "products", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<AssignedProducts> assignedProducts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "products", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<SoldProducts> soldProducts;

    public static Products valueOf(ProductsRequest products) {
        Products product=new Products();
    product.setProductName(products.getProductName());
    product.setDateAdded(LocalDateTime.now());
    product.setExpiryDate(products.getExpiryDate());
    product.setManufacturingCompany(products.getManufacturingCompany());
    product.setProductPrice(products.getProductPrice());
    product.setMaximumPrice(products.getMaximumPrice());
    product.setProductCode(products.getProductSize());
    product.setSellingPrice(products.getSellingPrice());
    product.setUnitsAvailable(Double.valueOf(products.getUnitsAvailable()));
        return product;
    }
}