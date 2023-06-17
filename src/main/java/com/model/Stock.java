package com.model;


import com.dto.stock.InputUpdateStockDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

    public Stock(Book book, Integer availableQuantity) {
        this.book = book;
        this.availableQuantity = availableQuantity;
    }

    public Stock(InputUpdateStockDTO inputUpdateStockDTO) {
        this.book = null;
        this.id = inputUpdateStockDTO.idStockItem();
        this.availableQuantity = inputUpdateStockDTO.availableQuantity();
    }
}
