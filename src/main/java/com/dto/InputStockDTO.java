package com.dto;

import com.model.Book;

public record InputStockDTO(Book book, Integer availableQuantity) {
}
