package com.unir.books.data;

import com.unir.books.model.pojo.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BookRepository {
    private final BookJpaRepository repository;

    public BookRepository(BookJpaRepository repository) {
        this.repository = repository;
    }

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Book getById(String isbn) {
        return repository.findById(isbn).orElse(null);
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public void delete(Book book) {
        repository.delete(book);
    }

//    public List<Book> search(String title, String category, String description, String author) {
//        SearchCriteria<Book> spec = new SearchCriteria<>();
//
//        if (StringUtils.isNotBlank(title)) {
//            spec.add(new SearchStatement("title", title, SearchOperation.MATCH));
//        }
//
//        if (StringUtils.isNotBlank(category)) {
//            spec.add(new SearchStatement("category", category, SearchOperation.MATCH));
//        }
//
//        if (StringUtils.isNotBlank(description)) {
//            spec.add(new SearchStatement("description", description, SearchOperation.MATCH));
//        }
//
//        if (StringUtils.isNotBlank(author)) {
//            spec.add(new SearchStatement("author", author, SearchOperation.MATCH));
//        }
//
//        return repository.findAll(spec);
//    }
}
