package com.unir.books.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.books.config.BookMapper;
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

//    @Override
//    public List<BookDto> getBooks(String title, String category, String description, String author) {
//        if (StringUtils.hasLength(title)
//                || StringUtils.hasLength(category)
//                || StringUtils.hasLength(description)
//                || StringUtils.hasLength(author)) {
//
//            List<Book> books = repository.search(title, category, description, author);
//
//        }
//
//        List<Book> books = repository.getBooks();
//
//        return books.isEmpty() ? null : books;
//    }


    @Override
    public List<BookDto> getBooks(String title, String category, String description, String author) {
        return null;
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

    /*
    @Override
    public Book updateBook(String bookIsbn, String request) {
        Book book = repository.getById(bookIsbn);

        if (book != null) {
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(request));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(book)));
                Book patched = objectMapper.treeToValue(target, Book.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating book {}", bookIsbn, e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Book updateBook(String bookIsbn, BookDto updateRequest) {
        Book book = repository.getById(bookIsbn);

        if (book != null) {
            book.update(updateRequest);
            repository.save(book);
            return book;
        } else {
            return null;
        }
    }

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
