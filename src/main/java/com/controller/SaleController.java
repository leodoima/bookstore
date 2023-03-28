package com.controller;

import com.model.Sale;
import com.model.SaleFormDTO;
import com.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> listAll() {
        return saleService.listAllSales();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Optional<Sale> findById(@PathVariable("id") Long id) {
        return saleService.findById(id);
    }

    @PostMapping
    public Sale generateSale(@RequestBody SaleFormDTO saleFormDTO) {
        return saleService.generateSale(saleFormDTO);
    }
}
