package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Book {

    private int id;
    private String title;
    private String description;
    private double price;

    private Author author;

    public Book(String title, String description, double price, Author author) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.author = author;

    }
}
