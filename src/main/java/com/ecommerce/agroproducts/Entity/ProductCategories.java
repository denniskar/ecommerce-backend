package com.ecommerce.agroproducts.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String categoryName;

    @OneToMany(fetch= FetchType.EAGER ,mappedBy = "productCategories",cascade = CascadeType.ALL)
    private Set<Products> products;
}
