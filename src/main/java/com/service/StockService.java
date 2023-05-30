package com.service;

import com.model.Book;
import com.model.Stock;
import com.dto.StockFormDTO;
import com.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BookService bookService;


    public List<Stock> listAllStock() {
        return stockRepository.findAll();
    }

    public Stock patchStock(Long id, StockFormDTO stockFormDTO) throws Exception {
        Stock stock = findStockById(id);
        // stock.setAvailableQuantity(stockFormDTO.getAvailableQuantity());

        return stockRepository.save(stock);
    }

    public Stock createStock(StockFormDTO stockFormDTO) throws Exception {
        Book book = validateBookInStock(stockFormDTO);
        Stock stock = new Stock(book, 12);

        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) throws Exception {
        findStockById(id);
        stockRepository.deleteById(id);
    }

    public Stock saleItem(Long id, int saleQuantity) throws Exception {
        Stock stock = findStockById(id);

        if (stock.getAvailableQuantity() < saleQuantity) {
            throw new Exception("Sale quantity is not disponible for this book");
        }

        stock.setAvailableQuantity(stock.getAvailableQuantity() - saleQuantity);

        return stockRepository.save(stock);
    }

    public Stock findStockById(Long id) {
        return stockRepository.findById(id).orElseThrow();
    }

    private Book validateBookInStock(StockFormDTO stockFormDTO) throws Exception {
        Book book = bookService.findBookById(stockFormDTO.getIdBook());
        Stock stock = stockRepository.findByBook(book);

        if (stock != null) {
            throw new Exception("Book is already exists in stock");
        }

        return book;
    }
}
