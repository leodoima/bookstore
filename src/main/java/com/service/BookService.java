package com.service;

import com.dto.InputBookDTO;
import com.helper.BookMapper;
import com.helper.Reflection;
import com.model.Book;
import com.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Book createBook(InputBookDTO inputBookDTO) {
        Book createBook = BookMapper.INSTANCE.toEntity(inputBookDTO);
        return bookRepository.save(createBook);
    }

    public Book updateBook(Long idBook, InputBookDTO inputBookDTO) {
        Book originalBook = findBookById(idBook);
        Book updateBook = BookMapper.INSTANCE.toEntity(inputBookDTO);

        Book unifiedBooks = (Book) Reflection.mutableObjects(originalBook, updateBook);

        return bookRepository.save(unifiedBooks);
    }

    public void deleteBookById(Long idBook) {
        Book book = findBookById(idBook);
        bookRepository.delete(book);
    }
}
