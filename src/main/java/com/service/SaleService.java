package com.service;

import com.model.Sale;
import com.dto.SaleFormDTO;
import com.model.Stock;
import com.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StockService stockService;


    public List<Sale> listAllSales() {
        return saleRepository.findAll();
    }

    public Sale generateSale(SaleFormDTO saleFormDTO) {
        if (saleFormDTO.getSaleQuantity() < 0 || saleFormDTO.getUnitSalePrice() <= 0) {
            return null;
        }

        Stock stock = stockService.saleItem(saleFormDTO.getIdStockItem(), saleFormDTO.getSaleQuantity());

        if (stock == null) {
            return null;
        }

        Sale sale = new Sale();
        sale.setStock(stock);
        sale.setSaleQuantity(saleFormDTO.getSaleQuantity());
        sale.setUnitSalePrice(saleFormDTO.getUnitSalePrice());

        return saleRepository.save(sale);
    }
}
