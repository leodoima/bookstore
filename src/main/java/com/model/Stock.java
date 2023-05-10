package com.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
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
    private int availableQuantity;

    public Stock(Book book, int availableQuantity) {
        this.book = book;
        this.availableQuantity = availableQuantity;
    }

    public Stock() {
    }

    public void setAvailableQuantity(int availableQuantity) throws Exception {
        validNewAvailableQuantity(availableQuantity);
        this.availableQuantity = availableQuantity;
    }

    private void validNewAvailableQuantity(int availableQuantity) throws Exception {
        if (availableQuantity < 0) {
            throw new Exception("Value for Available Quantity is not valid");
        }
    }
}
