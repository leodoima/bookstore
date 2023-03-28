package com.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

    @Column(name = "sale_quantity")
    private int saleQuantity;

    @Column(name = "price_unity")
    private double priceUnity;
}
