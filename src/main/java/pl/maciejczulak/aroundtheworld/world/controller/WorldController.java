package pl.maciejczulak.aroundtheworld.world.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.AirportRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;
import pl.maciejczulak.aroundtheworld.world.repository.ContinentRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;
import pl.maciejczulak.aroundtheworld.world.repository.HotelRepo;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/world")
public class WorldController {
    private static final Logger log = LoggerFactory.getLogger(WorldController.class);

    public ContinentRepo continentRepo;
    public CountryRepo countryRepo;
    public CityRepo cityRepo;
    public AirportRepo airportRepo;
    public HotelRepo hotelRepo;

    public WorldController(ContinentRepo continentRepo, CountryRepo countryRepo, CityRepo cityRepo, AirportRepo airportRepo, HotelRepo hotelRepo) {
        this.continentRepo = continentRepo;
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
        this.airportRepo = airportRepo;
        this.hotelRepo = hotelRepo;
    }

    @GetMapping("/test")
    public String test() {
        log.info("test");
        return "OK";
    }

    @PostMapping("/continents")
    ResponseEntity<Continent> addContinet(@RequestBody Continent toAdd){
        log.info("add continent");
        Continent result = continentRepo.save(toAdd);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }

    @GetMapping(value = "/continents")
    ResponseEntity<List<Continent>> readAllContinents(){
        log.info("read all continents");
        return ResponseEntity.ok(continentRepo.findAll());
    }

    @GetMapping("/continents/{id}")
    ResponseEntity<Continent> readContinentById(@PathVariable Integer id) {
        log.info("read continent by Id");
        return continentRepo.findById(id)
                .map(continent -> ResponseEntity.ok(continent))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/countries")
    ResponseEntity<Country> addCountry(@RequestBody Country toAdd){
        log.info("add country");
        Country result = countryRepo.save(toAdd);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }

    @GetMapping(value = "/countries")
    ResponseEntity<List<Country>> readAllCountries(){
        log.info("read all countries");
        return ResponseEntity.ok(countryRepo.findAll());
    }

    @GetMapping("/countries/{id}")
    ResponseEntity<Country> readCountryById(@PathVariable Integer id) {
        log.info("read country by Id");
        return countryRepo.findById(id)
                .map(country -> ResponseEntity.ok(country))
                .orElse(ResponseEntity.notFound().build());
    }




}
