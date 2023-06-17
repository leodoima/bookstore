package com.dto.book;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record InputUpdateBookDTO(
        @NotNull
        Long idBook,
        String title,
        String author,
        String publisher,
        Date publicationDate,
        Double salePrice) {
}
