package com.mapper;

import com.dto.*;
import com.model.Book;
import com.model.Stock;
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

    Stock toStockEntity(InputStockDTO inputStockDTO) throws Exception;
}
