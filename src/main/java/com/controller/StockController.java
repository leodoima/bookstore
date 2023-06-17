package com.controller;

import com.dto.stock.InputNewStockDTO;
import com.dto.stock.InputUpdateStockDTO;
import com.dto.stock.OutputDetailStockDTO;
import com.model.Stock;
import com.dto.InputStockDTO;
import com.service.StockService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{id}")
    public ResponseEntity<OutputDetailStockDTO> listOne(@PathVariable("id") Long idStockItem){
        OutputDetailStockDTO detailStockDTO = stockService.listOneItemStock(idStockItem);
        return ResponseEntity.ok(detailStockDTO);
    }

    @GetMapping
    public ResponseEntity<Page<OutputDetailStockDTO>> listAll(Pageable pageable) {
        var pageStocks = stockService.listAllStock(pageable);
        return ResponseEntity.ok(pageStocks);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OutputDetailStockDTO> create(@RequestBody @Valid InputNewStockDTO InputNewStockDTO, UriComponentsBuilder uriComponentsBuilder) throws Exception {
        OutputDetailStockDTO detailStockDTO = stockService.createStock(InputNewStockDTO);

        var uri = uriComponentsBuilder.path("/stocks/{id}").buildAndExpand(detailStockDTO.id()).toUri();
        return ResponseEntity.created(uri).body(detailStockDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<OutputDetailStockDTO> update(@RequestBody @Valid InputUpdateStockDTO inputUpdateStockDTO) throws Exception {
        OutputDetailStockDTO updatedStock = stockService.updateStock(inputUpdateStockDTO);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long idStockItem) {
        stockService.deleteStock(idStockItem);
        return ResponseEntity.noContent().build();
    }
}
