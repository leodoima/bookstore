package com.dto.book;

import com.model.Book;

import java.util.Date;

public record OutputDetailBookDTO(Long id, String title, String author, String publisher, Date publicationDate,
                                  Double salePrice) {

    public OutputDetailBookDTO(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublicationDate(), book.getSalePrice());
    }
}
