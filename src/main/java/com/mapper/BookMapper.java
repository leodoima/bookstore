package com.mapper;

import com.dto.book.InputNewBookDTO;
import com.dto.book.InputUpdateBookDTO;
import com.dto.book.OutputDetailBookDTO;
import com.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    Book convertToEntity(InputNewBookDTO inputNewBookDTO);

    @Mapping(target = "id", ignore = true)
    Book convertToEntity(InputUpdateBookDTO inputUpdateBookDTO);

    OutputDetailBookDTO convertToDTO(Book book);
}
