package pl.maciejczulak.aroundtheworld.trip.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.trip.model.Trip;
import pl.maciejczulak.aroundtheworld.trip.repository.TripRepo;
import pl.maciejczulak.aroundtheworld.world.model.Airport;
import pl.maciejczulak.aroundtheworld.world.repository.AirportRepo;

import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class TripService {
    private static final Logger log = LoggerFactory.getLogger(TripService.class);
    public TripRepo tripRepo;
    public TripService(TripRepo tripRepo) {
        this.tripRepo = tripRepo;
    }

    public List<Trip> getTripsList() {
        log.info("Getting trips list");
        return tripRepo.findAll();
    }

    public Optional<Trip> getTrip(Integer id) {
        Optional<Trip> tripOptional = tripRepo.findById(id);
        if(tripOptional.isEmpty()){
            log.info("Trip with id={} not found", id); }
        log.info("Getting trip with id={}", id);
        return tripOptional;
    }

    public Trip addTrip(Trip trip) {
        trip.setTripLenght(DAYS.between(trip.getStartDate(), trip.getEndDate()));
        log.info("Succesfully saved airport {} to database", trip.getName());
        return tripRepo.save(trip);
    }

    public Trip updateTrip (Integer id, Trip trip){
        Optional<Trip> airportOptional = tripRepo.findById(id);
        if(airportOptional.isEmpty()){
            log.info("Trip with id={} not found", id);
            return null;}
        trip.setId(id);
        tripRepo.save(trip);
        log.info("Trip with id={} successfully updated", id);
        return trip;
    }

    public void deleteTrip(Integer id) {
        Optional<Trip> tripOptional = tripRepo.findById(id);
        tripOptional.ifPresentOrElse(trip -> {
                    tripRepo.delete(trip);
                    log.info("Trip with id={} deleted from database", id);
                },
                () -> log.info("Trip with id={} not found", id)
        );
    }

}