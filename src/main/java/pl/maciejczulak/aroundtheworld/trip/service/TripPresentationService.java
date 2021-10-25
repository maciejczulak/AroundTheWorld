package pl.maciejczulak.aroundtheworld.trip.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.trip.model.Trip;
import pl.maciejczulak.aroundtheworld.trip.repository.TripRepo;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TripPresentationService {
    private static final Logger log = LoggerFactory.getLogger(TripPresentationService.class);
    public TripRepo tripRepo;
    public CountryRepo countryRepo;
    public CityRepo cityRepo;

    public TripPresentationService(TripRepo tripRepo, CountryRepo countryRepo, CityRepo cityRepo) {
        this.tripRepo = tripRepo;
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
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
                .filter(incoming -> incoming.getTripLenght()<=14)
                .collect(Collectors.toList());
    }

    public List<Trip> getIncoming3Trips(){
        log.info("Getting 3 incoming trips");
        List<Trip> trips = tripRepo.findAll();
        return trips.stream()
                .filter(incoming -> incoming.getTripLenght()<=14)
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Trip> getTripsByCountry(Integer countryId){
        log.info("Getting trips to the country with id={}", countryId);
        List<City> cities = cityRepo.findAll();
        List<City> citiesInCountry = cities.stream()
                .filter(c -> c.getCountryId().equals(countryId))
                .collect(Collectors.toList());

        List<Trip> trips = tripRepo.findAll();
        return trips.stream()
                .filter(c -> c.getDestCityId().equals(citiesInCountry))
                .collect(Collectors.toList());
    }


}
