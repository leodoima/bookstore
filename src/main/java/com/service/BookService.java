package com.service;

import com.model.Book;
import com.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) throws Exception {
        validateExistsBook(book.getId());
        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) throws Exception {
        validateExistsBook(id);
        bookRepository.deleteById(id);
    }

    private void validateExistsBook(Long id) throws Exception {
        if (!bookRepository.existsById(id)) {
            throw new Exception("Books is not found");
        }
    }
}
