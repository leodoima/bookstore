package com.service;

import com.dto.InputStockDTO;
import com.dto.stock.InputNewStockDTO;
import com.dto.stock.InputUpdateStockDTO;
import com.dto.stock.OutputDetailStockDTO;
import com.mapper.EntityMapper;
import com.facade.ReflectionFacade;
import com.model.Book;
import com.model.Stock;
import com.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BookService bookService;


    public OutputDetailStockDTO listOneItemStock(Long idStockItem) {
        Stock stockItem = findStockById(idStockItem);
        return convertToDTO(stockItem);
    }

    public Page<OutputDetailStockDTO> listAllStock(Pageable pageable) {
        return stockRepository.findAll(pageable).map(this::convertToDTO);
    }

    public OutputDetailStockDTO createStock(InputNewStockDTO inputNewStockDTO) throws Exception {
        Book book = validateEntryBookStock(inputNewStockDTO);

        Stock createdStockItem = stockRepository.save(new Stock(book, inputNewStockDTO.availableQuantity()));
        return convertToDTO(createdStockItem);
    }


    public OutputDetailStockDTO updateStock(InputUpdateStockDTO inputUpdateStockDTO) throws Exception {
        Stock updatedStock = updateStockDetails(inputUpdateStockDTO);

        if (!bookService.isExists(updatedStock.getBook().getId())) {
            throw new Exception("This book is not found");
        }
        return convertToDTO(stockRepository.save(updatedStock));
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
        return stock != null;
    }

    private OutputDetailStockDTO convertToDTO(Stock stock) {
        return new OutputDetailStockDTO(stock);
    }

    private Stock updateStockDetails(InputUpdateStockDTO inputUpdateStockDTO) {
        Stock originalStock = findStockById(inputUpdateStockDTO.idStockItem());
        return (Stock) ReflectionFacade.mutableObjects(originalStock, new Stock(inputUpdateStockDTO));
    }

    private Book validateEntryBookStock(InputNewStockDTO inputNewStockDTO) throws Exception {
        Book book = bookService.findBookById(inputNewStockDTO.idBook());

        if (book == null) throw new Exception("This book is not found");

        if (isExistsInStock(book)) throw new Exception("Record of this book already exists in stock");

        return book;
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
