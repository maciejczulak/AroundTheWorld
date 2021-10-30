package pl.maciejczulak.aroundtheworld.trip.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.trip.mapper.CityDTOMapper;
import pl.maciejczulak.aroundtheworld.trip.mapper.TripDTOMapper;
import pl.maciejczulak.aroundtheworld.trip.model.CityDTO;
import pl.maciejczulak.aroundtheworld.trip.model.Trip;
import pl.maciejczulak.aroundtheworld.trip.model.TripDTO;
import pl.maciejczulak.aroundtheworld.trip.repository.TripRepo;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class TripPresentationService {
    private static final Logger log = LoggerFactory.getLogger(TripPresentationService.class);
    public TripRepo tripRepo;
    public CountryRepo countryRepo;
    public CityRepo cityRepo;
    public TripDTOMapper mapper;

    public TripPresentationService(TripRepo tripRepo, CountryRepo countryRepo, CityRepo cityRepo, TripDTOMapper mapper) {
        this.tripRepo = tripRepo;
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
        this.mapper = mapper;
    }

    public List<Trip> getAllTripsList() {
        log.info("Getting all trips list");
        return tripRepo.findAll();
    }

    public List<Trip> getPromotedTrips() {
        log.info("Getting promoted trips");
        List<Trip> trips = tripRepo.findAll();
        return trips.stream()
                .filter(promoted -> promoted.getPromoted()==true)
                .collect(Collectors.toList());
    }

    public List<Trip> getPromoted3Trips() {
        log.info("Getting 3 promoted trips");
        List<Trip> trips = tripRepo.findAll();
        return trips.stream()
                .filter(promoted -> promoted.getPromoted()==true)
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Trip> getIncomingTrips(){
        log.info("Getting incoming trips");
        List<Trip> trips = tripRepo.findAll();
        return trips.stream()
                .filter(incoming -> DAYS.between(LocalDate.now(), incoming.getStartDate()) <= 14)
                .collect(Collectors.toList());
    }

    public List<Trip> getIncoming3Trips(){
        log.info("Getting 3 incoming trips");
        List<Trip> trips = tripRepo.findAll();
        return trips.stream()
                .filter(incoming -> DAYS.between(LocalDate.now(), incoming.getStartDate()) <=14)
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Trip> getTripsByDestCity(Integer cityId){
        log.info("Getting trips to the city with id={}", cityId);
        return tripRepo.findAllByDestCityId(cityId);
    }

    public List<Trip> getTripsByDestCountry(Integer countryId) {
        return tripRepo.findAll().stream()
                .map(a->mapper.mapTripToDTO(a))
                .filter(s->s.getDestCountryId().equals(countryId))
                .map(d->mapper.mapDTOtoTrip(d))
                .collect(Collectors.toList());
    }

    public List <Trip> getTripsByDestContinent(Integer continentId){

        return null;
    }








}
