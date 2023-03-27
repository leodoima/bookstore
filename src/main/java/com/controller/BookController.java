package com.controller;

import com.model.Book;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> listAll() {
        return bookService.listAllBooks();
    }

    @RequestMapping(value = "/{id}")
    public Optional<Book> findById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        if (bookService.findBookById(id).isPresent()) {
            bookService.deleteBookById(id);
        }
    }
}
