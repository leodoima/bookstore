package com.controller;

import com.dto.book.InputNewBookDTO;
import com.dto.book.InputUpdateBookDTO;
import com.dto.book.OutputDetailBookDTO;
import com.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<OutputDetailBookDTO> listOne(@PathVariable("id") Long idBook) {
        return ResponseEntity.ok(bookService.listOneBook(idBook));
    }

    @GetMapping
    public ResponseEntity<Page<OutputDetailBookDTO>> listAll(Pageable pageable) {
        var pageBooks = bookService.listAllBooks(pageable);
        return ResponseEntity.ok(pageBooks);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OutputDetailBookDTO> create(@RequestBody @Valid InputNewBookDTO inputNewBookDTO, UriComponentsBuilder uriComponentsBuilder) {
        OutputDetailBookDTO detailBookDTO = bookService.createBook(inputNewBookDTO);

        var uri = uriComponentsBuilder.path("/books/{id}").buildAndExpand(detailBookDTO.id()).toUri();
        return ResponseEntity.created(uri).body(detailBookDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<OutputDetailBookDTO> update(@RequestBody @Valid InputUpdateBookDTO inputUpdateBookDTO) {
        OutputDetailBookDTO detailBookDTO = bookService.updateBook(inputUpdateBookDTO);
        return ResponseEntity.ok(detailBookDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long idBook) {
        bookService.deleteBookById(idBook);
        return ResponseEntity.noContent().build();
    }
}
