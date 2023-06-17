package com.mapper;

import com.dto.InputNewBookDTO;
import com.dto.InputStockDTO;
import com.dto.InputUpdateBookDTO;
import com.dto.OutputDetailBookDTO;
import com.model.Book;
import com.model.Stock;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-15T01:09:56-0300",
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

    @Override
    public Stock toStockEntity(InputStockDTO inputStockDTO) throws Exception {
        if ( inputStockDTO == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setAvailableQuantity( inputStockDTO.availableQuantity() );
        stock.setBook( inputStockDTO.book() );

        return stock;
    }
}
