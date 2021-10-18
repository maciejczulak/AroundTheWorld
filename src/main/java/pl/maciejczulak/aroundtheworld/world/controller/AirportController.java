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
import pl.maciejczulak.aroundtheworld.world.model.Airport;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.repository.AirportRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/world/airport")
public class AirportController {
    private static final Logger log = LoggerFactory.getLogger(AirportController.class);

    public AirportRepo airportRepo;
    public AirportController(AirportRepo airportRepo) {
        this.airportRepo = airportRepo;
    }

    @PostMapping
    ResponseEntity<Airport> addAirport(@RequestBody Airport toAdd){
        log.info("add airport");
        Airport result = airportRepo.save(toAdd);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }

    @GetMapping
    ResponseEntity<List<Airport>> readAllAirports(){
        log.info("read all airports");
        return ResponseEntity.ok(airportRepo.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<Airport> readCityById(@PathVariable Integer id) {
        log.info("read airport by Id");
        return airportRepo.findById(id)
                .map(airport -> ResponseEntity.ok(airport))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<Airport> updateAiraportById(@PathVariable Integer id, @RequestBody Airport toUpdate){
        log.info("update airport by Id");
        if(!airportRepo.existsById(id)){
            return ResponseEntity.notFound().build();}
        toUpdate.setId(id);
        airportRepo.save(toUpdate);
        return ResponseEntity.noContent().build();
    }
}
