package emt.lab2.library.service.impl;

import emt.lab2.library.model.Country;
import emt.lab2.library.model.exceptions.CountryNotFoundException;
import emt.lab2.library.repository.CountryRepository;
import emt.lab2.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Country create(String name, String continent) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Country c = new Country(name,continent);
        countryRepository.save(c);
        return c;
    }

    @Override
    public Country update(String name, String continent) {
        Country country = this.countryRepository.findByName(name).orElseThrow(() -> new CountryNotFoundException(name));
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        country.setName(name);
        country.setContinent(continent);
        countryRepository.save(country);
        return country;
    }

    @Override
    public void delete(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.countryRepository.deleteByName(name);
    }

    @Override
    public List<Country> listCountries() {
        return this.countryRepository.findAll();
    }

    @Override
    public List<Country> searchCountris(String searchText) {
        return this.countryRepository.findAllByNameLike(searchText);
    }
}
