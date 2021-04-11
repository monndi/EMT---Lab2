package emt.lab2.library.web;

import emt.lab2.library.model.Country;
import emt.lab2.library.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/countries")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping
    public List<Country> findAll() {
        return this.countryService.listCountries();
    }

    @PostMapping("/add")
    public Country add(@RequestParam String name, @RequestParam String continent) {
        return this.countryService.create(name, continent);
    }
}
