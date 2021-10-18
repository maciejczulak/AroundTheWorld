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
import pl.maciejczulak.aroundtheworld.world.repository.AirportRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;
import pl.maciejczulak.aroundtheworld.world.repository.ContinentRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;
import pl.maciejczulak.aroundtheworld.world.repository.HotelRepo;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/world/continent")
public class ContinentController {
    private static final Logger log = LoggerFactory.getLogger(ContinentController.class);

    public ContinentRepo continentRepo;
    public ContinentController(ContinentRepo continentRepo) {
        this.continentRepo = continentRepo;
    }

    @PostMapping
    ResponseEntity<Continent> addContinet(@RequestBody Continent toAdd){
        log.info("add continent");
        Continent result = continentRepo.save(toAdd);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }

    @GetMapping
    ResponseEntity<List<Continent>> readAllContinents(){
        log.info("read all continents");
        return ResponseEntity.ok(continentRepo.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Continent> readContinentById(@PathVariable Integer id) {
        log.info("read continent by Id");
        return continentRepo.findById(id)
                .map(continent -> ResponseEntity.ok(continent))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<Continent> updateContinentById(@PathVariable Integer id, @RequestBody Continent toUpdate){
        log.info("update continent by Id");
        if(!continentRepo.existsById(id)){
        return ResponseEntity.notFound().build();}
        toUpdate.setId(id);
        continentRepo.save(toUpdate);
        return ResponseEntity.noContent().build();
    }
    }


