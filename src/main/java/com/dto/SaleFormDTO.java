package com.dto;

import lombok.Data;

@Data
public class SaleFormDTO {

    private Long idStockItem;

    private int saleQuantity;

    private double unitSalePrice;
}
