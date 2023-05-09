package com.builders;

import com.model.Stock;

public interface SaleBuilder {

    void validSaleQuantity(int saleQuantity) throws Exception;

    void validPriceValue(double priceValue) throws Exception;

    void getStock(Long idStock);

    void setDownsizeQuantityStock(int saleQuantity) throws Exception;

    void createRegisterSale(Stock stock, int saleQuantity, double priceValue);
}
