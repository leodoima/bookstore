package com.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record InputNewBookDTO(
        @NotBlank
        String title,
        @NotBlank
        String author,
        @NotBlank
        String publisher,

        Date publicationDate,
        @NotNull
        Double salePrice) {
}
