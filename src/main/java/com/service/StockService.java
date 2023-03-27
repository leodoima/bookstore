package com.service;

import com.model.Book;
import com.model.Stock;
import com.model.StockFormDTO;
import com.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
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
}
