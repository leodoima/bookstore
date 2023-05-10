package com.builders;

import com.dto.SaleFormDTO;
import com.model.Sale;
import com.model.Stock;
import com.repository.SaleRepository;
import com.repository.StockRepository;
import com.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookSaleBuilder implements SaleBuilder {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SaleRepository saleRepository;

    private Stock stock;

    private Sale sale;

    public BookSaleBuilder(SaleFormDTO saleFormDTO) throws Exception {
        this.validSaleQuantity(saleFormDTO.getSaleQuantity());
        this.validPriceValue(saleFormDTO.getUnitSalePrice());
        this.getStock(saleFormDTO.getIdStockItem());
        this.setDownsizeQuantityStock(saleFormDTO.getSaleQuantity());
        this.createRegisterSale(this.stock, saleFormDTO.getSaleQuantity(), saleFormDTO.getUnitSalePrice());
    }

    @Override
    public void validSaleQuantity(int saleQuantity) throws Exception {
        if (saleQuantity < 0) throw new Exception("Sale Quantity is not valid");
    }

    @Override
    public void validPriceValue(double priceValue) throws Exception {
        if (priceValue < 0) throw new Exception("Price Value is not valid");
    }

    @Override
    public void getStock(Long idStock) {
        this.stock = stockService.findStockById(idStock);
    }

    @Override
    public void setDownsizeQuantityStock(int saleQuantity) throws Exception {
        if (this.stock == null) {
            throw new Exception("Stock is not found for generated sale");
        }
        this.stock.setAvailableQuantity(this.stock.getAvailableQuantity() - saleQuantity);
        stockRepository.save(stock);
    }

    @Override
    public void createRegisterSale(Stock stock, int saleQuantity, double unitSalePrice) {
        this.sale = new Sale();
        this.sale.setStock(stock);
        this.sale.setSaleQuantity(saleQuantity);
        this.sale.setUnitSalePrice(unitSalePrice);

        saleRepository.save(this.sale);
    }

    public Sale getSale() {
        return this.sale;
    }
}
