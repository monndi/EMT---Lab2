package emt.lab2.library.model.dto;

import emt.lab2.library.enumerations.Category;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class BookDto {
    private String name;
    private Category category;
    private Long author_id;
    private Integer availableCopies;

    public BookDto(String name, Category category, Long author_id, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author_id = author_id;
        this.availableCopies = availableCopies;
    }

    public BookDto() {
    }
}
