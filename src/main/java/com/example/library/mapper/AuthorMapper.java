package com.example.library.mapper;

import com.example.library.model.Author;
import com.example.library.model.dto.AuthorDto;
import org.mapstruct.Mapper;

/**
 * @author Anatoliy Shikin
 */
@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    Author toEntity(AuthorDto authorDto);
}
