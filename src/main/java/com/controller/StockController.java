package com.controller;

import com.dto.InputStockDTO;
import com.model.Stock;
import com.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Stock> listAll() {
        return stockService.listAllStock();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Stock create(@RequestBody InputStockDTO inputStockDTO) throws Exception {
        return stockService.createStock(inputStockDTO);
    }

    @PatchMapping("/{id}")
    public Stock update(@PathVariable("id") Long idStock, @RequestBody InputStockDTO inputStockDTO) throws Exception {
        return stockService.updateStock(idStock, inputStockDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long idStock) {
        stockService.deleteStock(idStock);
    }
}
