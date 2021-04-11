package emt.lab2.library.model;

import emt.lab2.library.enumerations.Category;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private Category category;
    @ManyToOne
    private Author author;
    private Integer availableCopies;

    public Book() {
    }
    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.availableCopies = availableCopies;
        this.category = category;
        this.author = author;
    }

}
