package com.example.library.model.dto;

/**
 * @author Anatoliy Shikin
 */
public record BookDto(
        String title,
        String isbn,
        Integer publicationYear,
        AuthorDto authorDto
) {
}
