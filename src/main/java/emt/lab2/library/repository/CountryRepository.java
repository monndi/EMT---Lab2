package emt.lab2.library.repository;

import emt.lab2.library.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByName(String name);

    void deleteByName(String name);

    List<Country> findAllByNameLike(String text);
}
