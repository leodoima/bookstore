package com.helper;

import com.dto.InputBookDTO;
import com.model.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-30T13:21:45-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Eclipse Adoptium)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public Book toEntity(InputBookDTO inputBookDTO) {
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
}
