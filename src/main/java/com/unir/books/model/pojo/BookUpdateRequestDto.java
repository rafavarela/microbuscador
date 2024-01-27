package com.unir.books.model.pojo;

public class BookUpdateRequestDto {
    private String name;
    private String genre;
    private String author;
    private Integer pages;
    private String summary;

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPages() {
        return pages;
    }

    public String getSummary() {
        return summary;
    }
}
