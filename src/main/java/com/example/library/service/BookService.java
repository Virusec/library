package com.example.library.service;

import com.example.library.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Anatoliy Shikin
 */
public interface BookService {
    Page<BookDto> findAll(Pageable pageable);

    BookDto findByIsbn(String isbn);

    BookDto create(BookDto bookDto);

    BookDto update(String isbn, BookDto bookDto);

    void deleteByIsbn(String isbn);
}
