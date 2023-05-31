package com.service;

import com.dto.InputStockDTO;
import com.helper.EntityMapper;
import com.helper.Reflection;
import com.model.Book;
import com.model.Stock;
import com.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BookService bookService;


    public List<Stock> listAllStock() {
        return stockRepository.findAll();
    }

    public Stock createStock(InputStockDTO inputStockDTO) throws Exception {

        if (isExistsInStock(inputStockDTO.book())) {
            throw new Exception("Record of this book already exists in stock");
        }

        if (!bookService.isExists(inputStockDTO.book())) {
            throw new Exception("This book is not found");
        }

        Stock createStock = EntityMapper.INSTANCE.toStockEntity(inputStockDTO);
        return stockRepository.save(createStock);
    }

    public Stock updateStock(Long idStock, InputStockDTO inputStockDTO) throws Exception {
        Stock originalStock = findStockById(idStock);
        Stock updateStock = EntityMapper.INSTANCE.toStockEntity(inputStockDTO);

        Stock unifiedStocks = (Stock) Reflection.mutableObjects(originalStock, updateStock);

        if (!bookService.isExists(unifiedStocks.getBook())) {
            throw new Exception("This book is not found");
        }

        return stockRepository.save(unifiedStocks);
    }

    public void deleteStock(Long idStock) {
        Stock stock = findStockById(idStock);
        stockRepository.delete(stock);
    }

    public Stock findStockById(Long id) {
        return stockRepository.findById(id).orElseThrow();
    }

    private boolean isExistsInStock(Book book) {
        Stock stock = stockRepository.findByBook(book);

        return (stock == null) ? false : true;
    }

    public Stock saleItem(Long id, int saleQuantity) throws Exception {
        Stock stock = findStockById(id);

        if (stock.getAvailableQuantity() < saleQuantity) {
            throw new Exception("Sale quantity is not disponible for this book");
        }

        stock.setAvailableQuantity(stock.getAvailableQuantity() - saleQuantity);

        return stockRepository.save(stock);
    }
}
