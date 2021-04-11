package emt.lab2.library.service;

import emt.lab2.library.model.Country;

import java.util.List;

public interface CountryService {
    Country create(String name, String continent);

    Country update(String name, String continent);

    void delete(String name);

    List<Country> listCountries();

    List<Country> searchCountris(String searchText);
}
