package com.service;

import com.dto.InputBookDTO;
import com.helper.EntityMapper;
import com.helper.Reflection;
import com.model.Book;
import com.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
        Book createBook = EntityMapper.INSTANCE.toBookEntity(inputBookDTO);
        return bookRepository.save(createBook);
    }

    public Book updateBook(Long idBook, InputBookDTO inputBookDTO) {
        Book originalBook = findBookById(idBook);
        Book updateBook = EntityMapper.INSTANCE.toBookEntity(inputBookDTO);

        Book unifiedBooks = (Book) Reflection.mutableObjects(originalBook, updateBook);

        return bookRepository.save(unifiedBooks);
    }

    public void deleteBookById(Long idBook) {
        Book book = findBookById(idBook);
        bookRepository.delete(book);
    }

    public boolean isExists(Book book){
        return bookRepository.existsById(book.getId());
    }
}
