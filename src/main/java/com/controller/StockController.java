package com.controller;

import com.model.Stock;
import com.model.StockFormDTO;
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

    @GetMapping
    @RequestMapping(value = "/{id}")
    public Stock findById(@PathVariable Long id) {
        return null;
    }

    @GetMapping
    @RequestMapping(value = "/book/{idBook}")
    public Stock findByBook(@PathVariable("idBook") Long idBook) {
        return stockService.findStockByIdBook(idBook);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Stock create(@RequestBody StockFormDTO stockFormDTO) {
        return stockService.createStock(stockFormDTO);
    }

    @PutMapping("/{id}")
    public Stock update(@PathVariable("id") Long id, @RequestBody StockFormDTO stockFormDTO) {
        return stockService.updateStock(id, stockFormDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        stockService.deleteStock(id);
    }
}
