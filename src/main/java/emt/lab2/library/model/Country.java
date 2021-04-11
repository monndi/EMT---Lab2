package emt.lab2.library.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String continent;


    public Country() {
    }
    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

}
