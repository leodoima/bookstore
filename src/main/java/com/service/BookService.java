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

    private Book convertToBook(Optional<Book> optionalBook) {
        Book book = new Book();

        book.setId((optionalBook.get().getId()));
        book.setTitle(optionalBook.get().getTitle());
        book.setAuthor(optionalBook.get().getAuthor());
        book.setPublisher(optionalBook.get().getPublisher());
        book.setPublicationDate(optionalBook.get().getPublicationDate());
        book.setSalePrice(optionalBook.get().getSalePrice());

        return book;
    }

    private void validateExistsBook(Long id) throws Exception {
        if (!bookRepository.existsById(id)) {
            throw new Exception("Books is not found");
        }
    }

    private void validateExistsBook(Book book) throws Exception {
        if (!bookRepository.existsById(book.getId())) {
            throw new Exception("Books is not found");
        }
    }
}
