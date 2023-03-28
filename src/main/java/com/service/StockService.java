package com.service;

import com.model.Book;
import com.model.Stock;
import com.model.StockFormDTO;
import com.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Optional<Stock> findStockById(Long id) {
        return stockRepository.findById(id);
    }

    public Stock findStockByIdBook(Long idBook) {
        Optional<Book> optionalBook = bookService.findBookById(idBook);

        Book book = bookService.convertToBook(optionalBook);

        return stockRepository.findByBook(book);
    }

    public Stock updateStock(Long id, StockFormDTO stockFormDTO) {

        if (stockFormDTO.getAvailableQuantity() < 0) {
            return null;
        }

        Optional<Stock> optionalStock = findStockById(id);
        if (optionalStock.isEmpty()) {
            return null;
        }

        Stock stock = convertToStock(optionalStock);
        stock.setAvailableQuantity(stockFormDTO.getAvailableQuantity());

        return stockRepository.save(stock);
    }

    public Stock createStock(StockFormDTO stockFormDTO) {
        Optional<Book> optionalBook = bookService.findBookById(stockFormDTO.getIdBook());

        if (optionalBook.isEmpty()) {
            return null;
        }

        Book book = bookService.convertToBook(optionalBook);
        Stock stock = stockRepository.findByBook(book);

        if (stock != null) {
            return null;
        }

        stock = new Stock(book, stockFormDTO.getAvailableQuantity());
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
        }
    }

    public Stock saleItem(Long id, int saleQuantity) {

        if (saleQuantity < 0) {
            return null;
        }

        Optional<Stock> optionalStock = findStockById(id);

        if (optionalStock.isEmpty()) {
            return null;
        }

        Stock stock = convertToStock(optionalStock);

        if (stock.getAvailableQuantity() < saleQuantity) {
            return null;
        }

        stock.setAvailableQuantity(stock.getAvailableQuantity() - saleQuantity);

        return stockRepository.save(stock);
    }

    public Stock convertToStock(Optional<Stock> optionalStock) {
        Stock stock = new Stock();

        stock.setId(optionalStock.get().getId());
        stock.setBook(optionalStock.get().getBook());
        stock.setAvailableQuantity(optionalStock.get().getAvailableQuantity());

        return stock;
    }
}
