package emt.lab2.library.service;

import emt.lab2.library.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);

    List<Author> findAll();

    Optional<Author> save(String name, String surname, Long country_id );

    void deleteById(Long id);
}
