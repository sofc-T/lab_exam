package com.bookMid.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Book {
    private int id;
    private String title;
    private String author;
    private float price;


    public Book(String title, String author,  float price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}
