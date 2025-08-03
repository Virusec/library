package com.example.library.mapper;

import com.example.library.model.Book;
import com.example.library.model.dto.BookDto;
import org.mapstruct.Mapper;

/**
 * @author Anatoliy Shikin
 */
@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);
}
