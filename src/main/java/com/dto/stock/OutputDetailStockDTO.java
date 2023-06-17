package com.dto.stock;

import com.dto.book.OutputDetailBookDTO;
import com.model.Stock;

public record OutputDetailStockDTO(Long id, OutputDetailBookDTO outputDetailBookDTO, Integer availableQuantity) {
    public OutputDetailStockDTO(Stock stock) {
        this(stock.getId(), new OutputDetailBookDTO(stock.getBook()), stock.getAvailableQuantity());
    }
}
