package com.unir.books.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unir.books.model.pojo.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface BookJpaRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {
    List<Book> findByTitle(String title);
    List<Book> findByCategory(String category);
    List<Book> findByAuthor(String author);
    List<Book> findByDescription(String description);
    List<Book> findByTitleAndAuthor(String title, String author);
}
