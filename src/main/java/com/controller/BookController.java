package com.controller;

import com.model.Book;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> listAll() {
        return bookService.listAllBooks();
    }

    @GetMapping
    @RequestMapping(value = "/{id}")
    public Book findById(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book) throws Exception {
        return bookService.updateBook(book);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id) throws Exception {
        bookService.deleteBookById(id);
    }
}
