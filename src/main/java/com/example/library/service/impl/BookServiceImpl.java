package com.example.library.service.impl;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.dto.BookDto;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Anatoliy Shikin
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        Book book = findBook(isbn);
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        validateIsbn(bookDto.isbn());
        Author author = findAuthor(bookDto.authorDto().id());
        Book book = bookMapper.toEntity(bookDto);
        book.setAuthor(author);
        Book saved = bookRepository.save(book);
        return bookMapper.toDto(saved);
    }

    @Override
    public BookDto update(String isbn, BookDto bookDto) {
        Book existing = findBook(isbn);
        existing.setTitle(bookDto.title());
        existing.setPublicationYear(bookDto.publicationYear());
        Author author = findAuthor(bookDto.authorDto().id());
        existing.setAuthor(author);
        Book updated = bookRepository.save(existing);
        return bookMapper.toDto(updated);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        if (!bookRepository.existsByIsbn(isbn)) {
            throw new ResourceNotFoundException("Book not found with ISBN: " + isbn);
        }
        bookRepository.deleteByIsbn(isbn);
    }

    private Book findBook(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ISBN: " + isbn));
    }

    private void validateIsbn(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw new ResourceNotFoundException("Book already exists with ISBN: " + isbn);
        }
    }

    private Author findAuthor(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
    }
}
