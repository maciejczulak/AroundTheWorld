package pl.maciejczulak.aroundtheworld.trip.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejczulak.aroundtheworld.trip.model.Trip;
import pl.maciejczulak.aroundtheworld.trip.service.TripPresentationService;

import java.util.List;

@RestController
@RequestMapping("/trip/presentation")
public class TripPresentationController {
    private static final Logger log = LoggerFactory.getLogger(TripPresentationController.class);
    public TripPresentationService service;
    public TripPresentationController(TripPresentationService service) {
        this.service = service;
    }

    @GetMapping("/promoted")
    public List<Trip> getPromotedTrips(){
        log.info("Attempt to get promoted trips");
        return service.getPromotedTrips();
    }

    @GetMapping("/promoted3")
    public List<Trip> getPromoted3Trips(){
        log.info("Attempt to get 3 promoted trips");
        return service.getPromoted3Trips();
    }


    @GetMapping("/incoming")
    public List<Trip> getIncomingTrips(){
        log.info("Attempt to get incoming trips");
        return service.getIncomingTrips();
    }

    @GetMapping("/incoming3")
    public List<Trip> getIncoming3Trips(){
        log.info("Attempt to get 3 incoming trips");
        return service.getIncoming3Trips();
    }

    @GetMapping("/country/{countryId}")
    public List<Trip> getTripsByCountry(@PathVariable Integer countryId){
        log.info("Attempt to get trips to the country with id={}", countryId);
        return service.getTripsByCountry(countryId);
    }








}
