package pl.maciejczulak.aroundtheworld.world.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/world/country")
public class CountryController {
    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    public CountryRepo countryRepo;
    public CountryController(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    @PostMapping
    ResponseEntity<Country> addCountry(@RequestBody Country toAdd){
        log.info("add country");
        Country result = countryRepo.save(toAdd);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }

    @GetMapping
    ResponseEntity<List<Country>> readAllCountries(){
        log.info("read all countries");
        return ResponseEntity.ok(countryRepo.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Country> readCountryById(@PathVariable Integer id) {
        log.info("read country by Id");
        return countryRepo.findById(id)
                .map(country -> ResponseEntity.ok(country))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<Country> updateCountryById(@PathVariable Integer id, @RequestBody Country toUpdate){
        log.info("update country by Id");
        if(!countryRepo.existsById(id)){
            return ResponseEntity.notFound().build();}
        toUpdate.setId(id);
        countryRepo.save(toUpdate);
        return ResponseEntity.noContent().build();
    }
}
