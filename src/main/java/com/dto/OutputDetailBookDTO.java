package com.dto;

import java.util.Date;

public record OutputDetailBookDTO(Long id, String title, String author, String publisher, Date publicationDate,
                                  Double salePrice) {
}
