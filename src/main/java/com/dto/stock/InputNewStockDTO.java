package com.dto.stock;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record InputNewStockDTO(
        @NotNull
        Long idBook,
        @NotNull
        @PositiveOrZero
        Integer availableQuantity) {
}
