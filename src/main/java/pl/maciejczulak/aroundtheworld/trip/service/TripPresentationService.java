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
                .filter(incoming -> DAYS.between(LocalDate.now(), incoming.getStartDate()) <=14)
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
        return tripRepo.findTripsByDestCityId(cityId);
    }

    public List<Trip> getTripsByDestCountry(Integer countryId) {
        log.info("Getting trips to the country with id={}", countryId);
        List<Integer> citiesInCountryIds = cityRepo.findCitiesByCountryId(countryId).stream()
                .map(c -> Integer.valueOf(c.getId()))
                .collect(Collectors.toList());
        List<Trip> tripsInCountryList = new ArrayList<>();
        for(int i=0; i<=citiesInCountryIds.size()-1; i++){
               List<Trip> tripsInCity = tripRepo.findTripsByDestCityId(citiesInCountryIds.get(i));
               tripsInCountryList.addAll(tripsInCity);
        }
        return tripsInCountryList;
    }

    public List <Trip> getTripsByDestContinent(Integer continentId){
        log.info("Getting trips to the continent with id={}", continentId);
        List<Integer> countriesInContinentIds = countryRepo.findCountriesByContinentId(continentId).stream()
                .map(c -> Integer.valueOf(c.getId()))
                .collect(Collectors.toList());
        List<City> citiesOnContinent = new ArrayList<>();
        for(int i=0; i<=countriesInContinentIds.size()-1; i++){
            List<City> citiesInCountryList = cityRepo.findCitiesByCountryId(countriesInContinentIds.get(i));
            citiesOnContinent.addAll(citiesInCountryList);
        }
        List<Integer> citiesInContinentIds = citiesOnContinent.stream()
                .map(c -> Integer.valueOf(c.getId()))
                .collect(Collectors.toList());
        List<Trip> tripsInContinentList = new ArrayList<>();
        for(int i=0; i<=citiesInContinentIds.size()-1; i++){
            List<Trip> tripsInCountry = tripRepo.findTripsByDestCityId(citiesInContinentIds.get(i));
            tripsInContinentList.addAll(tripsInCountry);
        }
        return tripsInContinentList;
    }








}
