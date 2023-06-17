package com.model;

import com.dto.book.InputNewBookDTO;
import com.dto.book.InputUpdateBookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String author;
    private String publisher;
    private Date publicationDate;
    private Double salePrice;

    public Book(InputUpdateBookDTO inputUpdateBookDTO) {
        this(inputUpdateBookDTO.idBook(), inputUpdateBookDTO.title(), inputUpdateBookDTO.author(), inputUpdateBookDTO.publisher(), inputUpdateBookDTO.publicationDate(), inputUpdateBookDTO.salePrice());
    }
    public Book(InputNewBookDTO inputNewBookDTO) {
        this(null, inputNewBookDTO.title(), inputNewBookDTO.author(), inputNewBookDTO.publisher(), inputNewBookDTO.publicationDate(), inputNewBookDTO.salePrice());
    }
}