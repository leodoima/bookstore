package com.dto;

import java.util.Date;

public record InputBookDTO(String title, String author, String publisher, Date publicationDate, Double salePrice) {
}
