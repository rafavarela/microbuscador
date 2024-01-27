package com.unir.books.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.books.config.BookMapper;
import com.unir.books.model.pojo.BookUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.books.data.BookRepository;
import com.unir.books.model.pojo.Book;
import com.unir.books.model.pojo.BookDto;

@Service
@Slf4j
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<BookDto> getBooks(String title, String category, String description, String author) {
        List<Book> books;
        if (StringUtils.hasLength(title)
                || StringUtils.hasLength(category)
                || StringUtils.hasLength(description)
                || StringUtils.hasLength(author)) {

            books = repository.search(title, category, description, author);
        } else {
            books = repository.getBooks();
        }

        List<BookDto> bookDtos = mapper.toBooksDto(books);

        return books.isEmpty() ? null : bookDtos;
    }

    @Override
    public BookDto getBook(String bookIsbn) {
        Book book = repository.getById(bookIsbn);
        return mapper.toBookDto(book);
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        if (bookDto != null
                && StringUtils.hasLength(bookDto.getId().trim())
                && StringUtils.hasLength(bookDto.getName().trim())) {

            Book bookToInsert = mapper.toBook(bookDto);
            Book bookInserted = repository.save(bookToInsert);

            return mapper.toBookDto(bookInserted);
        } else {
            return null;
        }
    }


    @Override
    public BookDto updateBook(String bookIsbn, String request) {
        Book book = repository.getById(bookIsbn);

        if (book != null) {
            try {
                BookDto bookDto = mapper.toBookDto(book);
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(bookDto)));
                BookDto patched = objectMapper.treeToValue(target, BookDto.class);
                repository.save(mapper.toBook(patched));

                return patched;

            } catch (JsonProcessingException | JsonPatchException e) {
                //log.error("Error updating book {}", bookIsbn, e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public BookDto updateBook(String bookIsbn, BookUpdateRequestDto updateRequest) {
        Book book = repository.getById(bookIsbn);

        if (book != null) {
            book.update(updateRequest); // Mutation
            repository.save(book);
            return mapper.toBookDto(book);
        } else {
            return null;
        }
    }

    /*
    @Override
    public Boolean removeBook(String bookIsbn) {
        Book book = repository.getById(bookIsbn);
        if (book != null) {
            repository.delete(book);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    */
}
