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
import pl.maciejczulak.aroundtheworld.world.model.Airport;
import pl.maciejczulak.aroundtheworld.world.service.AirportService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/world/airport")
public class AirportController {
    private static final Logger log = LoggerFactory.getLogger(AirportController.class);
    public AirportService airportService;
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAirportsList(){
        log.info("Attempt to get airports list");
        return airportService.getAirportsList();
    }

    @GetMapping("/{id}")
    public Optional<Airport> getAirport(@PathVariable Integer id){
        log.info("Attempt to get airport with id={}", id);
        return airportService.getAirport(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Airport addAirport(@RequestBody Airport airport){
        log.info("Attempt to save airport {} to database", airport.getName());
        return airportService.addAirport(airport);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Airport updateAirport(@PathVariable Integer id, @RequestBody Airport toUpdate){
        log.info("Attempt to update airport with id={}", id);
        return airportService.updateAirport(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAirport(@PathVariable Integer id){
        log.info("Attempt to delete airport with id={}", id);
        airportService.deleteAirport(id);
    }
}
