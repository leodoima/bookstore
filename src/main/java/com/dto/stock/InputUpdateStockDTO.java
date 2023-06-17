package com.dto.stock;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record InputUpdateStockDTO(
        @NotNull
        Long idStockItem,
        @NotNull
        @PositiveOrZero
        Integer availableQuantity) {
}
