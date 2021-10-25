package pl.maciejczulak.aroundtheworld.trip.controller;

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
import pl.maciejczulak.aroundtheworld.trip.model.Trip;
import pl.maciejczulak.aroundtheworld.trip.service.TripService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trip")
public class TripController {
    private static final Logger log = LoggerFactory.getLogger(TripController.class);
    public TripService tripService;
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public List<Trip> getTripsList(){
        log.info("Attempt to get trip list");
        return tripService.getTripsList();
    }

    @GetMapping("/{id}")
    public Optional<Trip> getTrip(@PathVariable Integer id){
        log.info("Attempt to get trip with id={}", id);
        return tripService.getTrip(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Trip addTrip(@RequestBody Trip trip){
        log.info("Attempt to save trip {} to database", trip.getName());
        return tripService.addTrip(trip);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public Trip updateTrip(@PathVariable Integer id, @RequestBody Trip toUpdate){
        log.info("Attempt to update trip with id={}", id);
        return tripService.updateTrip(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Integer id){
        log.info("Attempt to delete trip with id={}", id);
        tripService.deleteTrip(id);
    }

}
