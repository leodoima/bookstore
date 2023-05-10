package com.controller;

import com.model.Stock;
import com.dto.StockFormDTO;
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
    public Stock create(@RequestBody StockFormDTO stockFormDTO) throws Exception {
        return stockService.createStock(stockFormDTO);
    }

    @PatchMapping("/{id}")
    public Stock patch(@PathVariable("id") Long id, @RequestBody StockFormDTO stockFormDTO) throws Exception {
        return stockService.patchStock(id, stockFormDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws Exception {
        stockService.deleteStock(id);
    }
}
