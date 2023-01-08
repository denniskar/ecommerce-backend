package com.ecommerce.agroproducts.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoldProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sellingPrice", length = 30)
    private  String sellingPrice;
    @Column(name = "quantity", length = 30)
    private  Double quantity;
    @Column(name = "profit", length = 30)
    private Double profit;
    @Column(name="transactionDate",updatable = false,insertable = false,nullable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeSold;
    @JoinColumn(name="userId",referencedColumnName = "id")
    @ManyToOne(cascade =CascadeType.DETACH,fetch = FetchType.LAZY,optional = false)
    private  Users users;

    @JoinColumn(name="productId",referencedColumnName = "id")
    @ManyToOne(cascade =CascadeType.DETACH,fetch = FetchType.LAZY,optional = false)
    private  Products products;
}
