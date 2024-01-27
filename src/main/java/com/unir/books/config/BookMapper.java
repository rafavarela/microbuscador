package com.unir.books.config;

import com.unir.books.model.pojo.Book;
import com.unir.books.model.pojo.BookDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mappings({
            @Mapping(source = "id", target = "isbn"),
            @Mapping(source = "name", target = "title"),
            @Mapping(source = "genre", target = "category"),
            @Mapping(source = "author", target = "author"),
            @Mapping(source = "pages", target = "pageCount"),
            @Mapping(source = "summary", target = "description")
    })
    Book toBook(BookDto bookDto);
    List<Book> toBooks(List<BookDto> booksDto);

    @InheritInverseConfiguration
    BookDto toBookDto(Book book);

    @InheritInverseConfiguration
    List<BookDto> toBooksDto(List<Book> books);
}
