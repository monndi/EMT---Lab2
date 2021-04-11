package emt.lab2.library.service;

import emt.lab2.library.enumerations.Category;
import emt.lab2.library.model.Author;
import emt.lab2.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, Category category, Long author_id, Integer availableCopies);

    Optional<Book> edit(Long id, String name, Category category, Long author_id, Integer availableCopies);

    void deleteById(Long id);

    Page<Book> findAllWithPagination(Pageable pageable);

    Book rentBook(Long id);
}
