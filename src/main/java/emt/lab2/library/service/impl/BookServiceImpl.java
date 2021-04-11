package emt.lab2.library.service.impl;

import emt.lab2.library.model.exceptions.AuthorNotFoundException;
import emt.lab2.library.enumerations.Category;
import emt.lab2.library.model.Author;
import emt.lab2.library.model.Book;
import emt.lab2.library.model.exceptions.BookNotFoundException;
import emt.lab2.library.repository.AuthorRepository;
import emt.lab2.library.repository.BookRepository;
import emt.lab2.library.repository.CountryRepository;
import emt.lab2.library.service.BookService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;


    public BookServiceImpl(BookRepository bookRepository, CountryRepository countryRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll().stream().sorted((x, y) -> x.getName().compareTo(y.getName())).collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long author_id, Integer availableCopies) {
        Author author = this.authorRepository.findById(author_id).orElseThrow(() -> new AuthorNotFoundException(author_id));
        this.bookRepository.deleteByName(name);
        Book book = new Book(name, category, author, availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long author_id, Integer availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setName(name);
        book.setCategory(category);
        book.setAvailableCopies(availableCopies);
        Author author = this.authorRepository.findById(author_id).orElseThrow(() -> new AuthorNotFoundException(author_id));
        book.setAuthor(author);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Book rentBook(Long id) {
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (book.getAvailableCopies()>0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.edit(book.getId(), book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies());
        }
        else throw new BookNotFoundException(id);
        return book;
    }
}
