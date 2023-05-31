package com.controller;

import com.dto.InputBookDTO;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody InputBookDTO inputBookDTO) {
        return bookService.createBook(inputBookDTO);
    }

    @PatchMapping
    @RequestMapping(value = "/{id}")
    public Book update(@PathVariable("id") Long idBook, @RequestBody InputBookDTO inputBookDTO) {
        return bookService.updateBook(idBook, inputBookDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long idBook) {
        bookService.deleteBookById(idBook);
    }
}
