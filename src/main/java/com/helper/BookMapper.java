package com.helper;

import com.dto.InputBookDTO;
import com.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toEntity(InputBookDTO inputBookDTO);
}