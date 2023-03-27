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

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {

        if (findBookById(book.getId()).isEmpty()) {
            return null;
        }
        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        if (findBookById(id).isPresent()) {
            bookRepository.deleteById(id);
        }
    }

    public Book convertToBook(Optional<Book> optionalBook) {
        Book book = new Book();

        book.setId((optionalBook.get().getId()));
        book.setTitle(optionalBook.get().getTitle());
        book.setAuthor(optionalBook.get().getAuthor());
        book.setPublisher(optionalBook.get().getPublisher());
        book.setPublication(optionalBook.get().getPublication());
        book.setPrice(optionalBook.get().getPrice());

        return book;
    }
}
