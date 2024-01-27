package com.unir.books.service;

import java.util.List;
import com.unir.books.model.pojo.BookDto;

public interface BooksService {
    List<BookDto> getBooks(String title, String category, String description, String author);

    BookDto getBook(String bookIsbn);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(String bookIsbn, String updateRequest);

    //Book updateBook(String bookIsbn, BookDto updateRequest);

    //Boolean removeBook(String bookIsbn);
}
