package pl.maciejczulak.aroundtheworld.world.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.repository.AirportRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;
import pl.maciejczulak.aroundtheworld.world.repository.ContinentRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;
import pl.maciejczulak.aroundtheworld.world.repository.HotelRepo;
import pl.maciejczulak.aroundtheworld.world.service.ContinentService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/world/continent")
public class ContinentController {
    private static final Logger log = LoggerFactory.getLogger(ContinentController.class);
    public ContinentService continentService;
    public ContinentController(ContinentService continentService) {
        this.continentService = continentService;
    }

    @GetMapping
    public List<Continent> getContinentsList(){
        log.info("Attempt to get continents list");
        return continentService.getContinentsList();
    }

    @GetMapping("/{id}")
    public Optional<Continent> getContinent(@PathVariable Integer id){
        log.info("Attempt to get continent with id={}", id);
        return continentService.getContinent(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Continent addContinent(@RequestBody Continent continent){
        log.info("Attempt to save continent {} to database", continent.getName());
        return continentService.addContinent(continent);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    Continent updateContinent(@PathVariable Integer id, @RequestBody Continent toUpdate){
        log.info("Attempt to update continent with id={}", id);
        return continentService.updateContinent(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteContinent(@PathVariable Integer id){
        log.info("Attempt to delete continent with id={}", id);
        continentService.deleteContinent(id);
    }
    }


