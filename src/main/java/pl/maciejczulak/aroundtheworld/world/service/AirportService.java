package pl.maciejczulak.aroundtheworld.world.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.world.model.Airport;
import pl.maciejczulak.aroundtheworld.world.repository.AirportRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    private static final Logger log = LoggerFactory.getLogger(AirportService.class);
    public AirportRepo airportRepo;
    public AirportService(AirportRepo airportRepo) {
        this.airportRepo = airportRepo;
    }

    public List<Airport> getAirportsList() {
        log.info("Getting airports list");
        return airportRepo.findAll();
    }

    public Optional<Airport> getAirport(Integer id) {
        Optional<Airport> airportOptional = airportRepo.findById(id);
        if(airportOptional.isEmpty()){
            log.info("Airport with id={} not found", id); }
        log.info("Airport city with id={}", id);
        return airportOptional;
    }

    public Airport addAirport(Airport airport) {
        log.info("Succesfully saved airport {} to database", airport.getName());
        return airportRepo.save(airport);
    }

    public Airport updateAirport (Integer id, Airport airport){
        Optional<Airport> airportOptional = airportRepo.findById(id);
        if(airportOptional.isEmpty()){
            log.info("Airport with id={} not found", id);
            return null;}
        airport.setId(id);
        airportRepo.save(airport);
        log.info("Airport with id={} successfully updated", id);
        return airport;
    }

    public void deleteAirport(Integer id) {
        Optional<Airport> airportOptional = airportRepo.findById(id);
        airportOptional.ifPresentOrElse(airport -> {
                    airportRepo.delete(airport);
                    log.info("Airport with id={} deleted from database", id);
                },
                () -> log.info("Airport with id={} not found", id)
        );
    }

}