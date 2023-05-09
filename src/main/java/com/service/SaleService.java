package com.service;

import com.builders.BookSaleBuilder;
import com.model.Sale;
import com.dto.SaleFormDTO;
import com.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private StockService stockService;


    public List<Sale> listAllSales() {
        return saleRepository.findAll();
    }

    public Sale generateSale(SaleFormDTO saleFormDTO) throws Exception {
        return new BookSaleBuilder(saleFormDTO).getSale();
    }
}
