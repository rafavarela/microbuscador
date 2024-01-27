package com.unir.books.model.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "books", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {
    @Id
    @Column(name="isbn")
    private String isbn;

    @Column(name="title")
    private String title;

    @Column(name="category")
    private String category;

    @Column(name="author")
    private String author;

    @Column(name="page_count")
    private Integer pageCount;

    @Column(name="description")
    private String description;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
    public void update(BookDto bookDto) {
        this.title = bookDto.getTitle();
        this.category = bookDto.getCategory();
        this.author = bookDto.getAuthor();
        this.description = bookDto.getDescription();
    }
    */

}
