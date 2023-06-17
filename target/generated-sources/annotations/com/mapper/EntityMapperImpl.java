package com.mapper;

import com.dto.InputBookDTO;
import com.dto.InputStockDTO;
import com.model.Book;
import com.model.Stock;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-14T21:49:10-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
public class EntityMapperImpl implements EntityMapper {

    @Override
    public Book toBookEntity(InputBookDTO inputBookDTO) {
        if ( inputBookDTO == null ) {
            return null;
        }

        Book book = new Book();

        book.setTitle( inputBookDTO.title() );
        book.setAuthor( inputBookDTO.author() );
        book.setPublisher( inputBookDTO.publisher() );
        book.setPublicationDate( inputBookDTO.publicationDate() );
        book.setSalePrice( inputBookDTO.salePrice() );

        return book;
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
