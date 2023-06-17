package com.mapper;

import com.dto.book.InputNewBookDTO;
import com.dto.book.InputUpdateBookDTO;
import com.dto.book.OutputDetailBookDTO;
import com.model.Book;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-17T01:28:47-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public Book convertToEntity(InputNewBookDTO inputNewBookDTO) {
        if ( inputNewBookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setTitle( inputNewBookDTO.title() );
        book.setAuthor( inputNewBookDTO.author() );
        book.setPublisher( inputNewBookDTO.publisher() );
        book.setPublicationDate( inputNewBookDTO.publicationDate() );
        book.setSalePrice( inputNewBookDTO.salePrice() );

        return book;
    }

    @Override
    public Book convertToEntity(InputUpdateBookDTO inputUpdateBookDTO) {
        if ( inputUpdateBookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setTitle( inputUpdateBookDTO.title() );
        book.setAuthor( inputUpdateBookDTO.author() );
        book.setPublisher( inputUpdateBookDTO.publisher() );
        book.setPublicationDate( inputUpdateBookDTO.publicationDate() );
        book.setSalePrice( inputUpdateBookDTO.salePrice() );

        return book;
    }

    @Override
    public OutputDetailBookDTO convertToDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String author = null;
        String publisher = null;
        Date publicationDate = null;
        Double salePrice = null;

        id = book.getId();
        title = book.getTitle();
        author = book.getAuthor();
        publisher = book.getPublisher();
        publicationDate = book.getPublicationDate();
        salePrice = book.getSalePrice();

        OutputDetailBookDTO outputDetailBookDTO = new OutputDetailBookDTO( id, title, author, publisher, publicationDate, salePrice );

        return outputDetailBookDTO;
    }
}
