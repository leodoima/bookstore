package com.service;

import com.model.Book;
import com.model.Stock;
import com.dto.StockFormDTO;
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

    public Stock patchStock(Long id, StockFormDTO stockFormDTO) throws Exception {

        /*
         * Pode ser reduzido ainda mais (avaliar)
         * */
        Stock stock = findStockById(id);
        stock.setAvailableQuantity(stockFormDTO.getAvailableQuantity());

        return stockRepository.save(stock);
    }

    public Stock createStock(StockFormDTO stockFormDTO) {
        // Buscar livro pelo ID
        // Validar se livro já encontra-se no estoque
        // Criar registro no estoque

        Book book = bookService.findBookById(stockFormDTO.getIdBook());



        stock = new Stock(book, stockFormDTO.getAvailableQuantity());
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
        }
    }

    public Stock saleItem(Long id, int saleQuantity) {

        // Validar quantidade de venda
        // Verificar se item do estoque existe
        // Aplicar update de quantidades de estoque
        // Salvar venda

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

    private Stock convertToStock(Optional<Stock> optionalStock) throws Exception {
        Stock stock = new Stock();

        stock.setId(optionalStock.get().getId());
        stock.setBook(optionalStock.get().getBook());
        stock.setAvailableQuantity(optionalStock.get().getAvailableQuantity());

        return stock;
    }

    private Stock findStockById(Long id) throws Exception {

        Optional<Stock> optionalStock = stockRepository.findById(id);
        if (optionalStock.isEmpty()) {
            throw new Exception("Stock is not found");
        }

        Stock stock = convertToStock(optionalStock);
        return stock;
    }

    private Stock validateBookInStock(Book book){
        Stock stock = stockRepository.findByBookStock(book);

        if (stock != null) {
            throw new Exception("");
        }

        return stock;
    }
}
