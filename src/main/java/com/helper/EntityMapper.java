package com.helper;

import com.dto.InputBookDTO;
import com.dto.InputStockDTO;
import com.model.Book;
import com.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityMapper {

    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    Book toBookEntity(InputBookDTO inputBookDTO);

    Stock toStockEntity(InputStockDTO inputStockDTO) throws Exception;
}