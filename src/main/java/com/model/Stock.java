package com.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
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

    public Stock(Book book, int availableQuantity) throws Exception {
        validSetAvailableQuantity(availableQuantity);

        this.book = book;
        this.availableQuantity = availableQuantity;
    }

    public Stock() {
    }

    public void setAvailableQuantity(Integer availableQuantity) throws Exception {
        validSetAvailableQuantity(availableQuantity);
        this.availableQuantity = availableQuantity;
    }

    private void validSetAvailableQuantity(Integer availableQuantity) throws Exception {
        if (availableQuantity < 0) {
            throw new Exception("Value for Available Quantity is not valid");
        }
    }
}
