package emt.lab2.library.service.impl;

import emt.lab2.library.model.Author;
import emt.lab2.library.model.Country;
import emt.lab2.library.model.exceptions.CountryNotFoundException;
import emt.lab2.library.repository.AuthorRepository;
import emt.lab2.library.repository.CountryRepository;
import emt.lab2.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(String name, String surname, Long country_id ) {

        Country country = countryRepository.findById(country_id).orElseThrow(() -> new CountryNotFoundException(country_id));
        return Optional.of(this.authorRepository.save(new Author(name, surname, country)));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
