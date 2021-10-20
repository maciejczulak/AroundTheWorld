package pl.maciejczulak.aroundtheworld.world.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.service.CountryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/world/country")
public class CountryController {
    private static final Logger log = LoggerFactory.getLogger(CountryController.class);
    public CountryService countryService;
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getCountriesList(){
        log.info("Attempt to get countries list");
        return countryService.getCountriesList();
    }

    @GetMapping("/{id}")
    public Optional<Country> getCountry(@PathVariable Integer id){
        log.info("Attempt to get country with id={}", id);
        return countryService.getCountry(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Country addCountry(@RequestBody Country country){
        log.info("Attempt to save country {} to database", country.getName());
        return countryService.addCountry(country);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable Integer id, @RequestBody Country toUpdate){
        log.info("Attempt to update country with id={}", id);
        return countryService.updateCountry(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Integer id){
        log.info("Attempt to delete country with id={}", id);
        countryService.deleteCountry(id);
    }
}
