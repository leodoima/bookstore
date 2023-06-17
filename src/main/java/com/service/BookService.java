package com.service;

import com.dto.InputNewBookDTO;
import com.dto.InputUpdateBookDTO;
import com.dto.OutputDetailBookDTO;
import com.mapper.BookMapper;
import com.facade.ReflectionFacade;
import com.model.Book;
import com.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public OutputDetailBookDTO listOneBook(Long id) {
        Book book = findBookById(id);
        return convertToDTO(book);
    }

    public Page<OutputDetailBookDTO> listAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).map(this::convertToDTO);
    }

    public OutputDetailBookDTO createBook(InputNewBookDTO inputNewBookDTO) {
        Book createdBook = bookRepository.save(convertToBook(inputNewBookDTO));
        return convertToDTO(createdBook);
    }

    public OutputDetailBookDTO updateBook(InputUpdateBookDTO inputUpdateBookDTO) {
        Book updatedBook = updateBookDetails(inputUpdateBookDTO);

        return convertToDTO(bookRepository.save(updatedBook));
    }

    public void deleteBookById(Long idBook) {
        Book book = findBookById(idBook);
        bookRepository.delete(book);
    }

    public boolean isExists(Book book) {
        return bookRepository.existsById(book.getId());
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    private Book convertToBook(InputNewBookDTO inputNewBookDTO) {
        return BookMapper.INSTANCE.convertToEntity(inputNewBookDTO);
    }

    private Book convertToBook(InputUpdateBookDTO inputUpdateBookDTO) {
        return BookMapper.INSTANCE.convertToEntity(inputUpdateBookDTO);
    }

    private Book updateBookDetails(InputUpdateBookDTO inputUpdateBookDTO) {
        Book originalBook = findBookById(inputUpdateBookDTO.idBook());

        return (Book) ReflectionFacade.mutableObjects(originalBook, convertToBook(inputUpdateBookDTO));
    }

    private OutputDetailBookDTO convertToDTO(Book book) {
        return BookMapper.INSTANCE.convertToDTO(book);
    }
}
