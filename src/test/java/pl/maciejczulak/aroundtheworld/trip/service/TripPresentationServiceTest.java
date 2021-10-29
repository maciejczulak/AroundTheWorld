package pl.maciejczulak.aroundtheworld.trip.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.maciejczulak.aroundtheworld.trip.model.Trip;
import pl.maciejczulak.aroundtheworld.trip.repository.TripRepo;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;
import pl.maciejczulak.aroundtheworld.world.repository.ContinentRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TripPresentationServiceTest {
    final static Continent CONTINENT1 = new Continent(1, "Europe");

    final static Country COUNTRY1  = new Country(1, "France", 1);
    final static Country COUNTRY2  = new Country(2, "Spain", 1);
    List <Country> allCountries = List.of(COUNTRY1, COUNTRY2);

    final static City CITY1 = new City(1, "Paris", 1);
    final static City CITY2 = new City(2, "Lyon", 1);
    final static City CITY3 = new City(3, "Nice", 1);
    final static City CITY4 = new City(4, "Madrid", 2);
    final static City CITY5 = new City(5, "Barcelona", 2);
    final static City CITY6 = new City(6, "Sevilla", 2);
    List <City> allCities = List.of(CITY1, CITY2, CITY3, CITY4, CITY5, CITY6);

    final static LocalDate INCOMING_DATE = LocalDate.of(2021, 11, 6);
    final static Trip TRIP1 = new Trip(1, "Francja-elegancja", 4, null, 1, null, null, INCOMING_DATE, null, null, null, null, true);
    final static Trip TRIP2 = new Trip(2, "Francja-elokwencja", 4, null, 1, null, null, INCOMING_DATE, null, null, null, null, true);
    final static Trip TRIP3 = new Trip(3, "Słoneczna hiszpania", 1, null, 5, null, null, INCOMING_DATE, null, null, null, null, false);
    List <Trip> allTrips = List.of(TRIP1, TRIP2, TRIP3);

    @Mock
    private ContinentRepo continentRepo;
    @Mock
    private CountryRepo countryRepo;
    @Mock
    private CityRepo cityRepo;
    @Mock
    private TripRepo tripRepo;

    @InjectMocks
    private TripPresentationService tripPresentationService;

    @Test
    void shouldGetPromotedTrips() {
        when(tripRepo.findAll()).thenReturn(allTrips);
        List<Trip> promotedTrips = tripPresentationService.getPromotedTrips();
        assertThat(promotedTrips.size()).isEqualTo(2);
        }

    @Test
    void shouldGetIncomingTrips(){
        when(tripRepo.findAll()).thenReturn(allTrips);
        List<Trip> incomingTrips = tripPresentationService.getIncomingTrips();
        assertThat(incomingTrips.size()).isEqualTo(3);
    }

    @Test
    void shouldGetTripsByDestCityId(){
        int searchingId = 1;
        when(tripRepo.findAll()).thenReturn(allTrips);
        List <Trip> actualTrips = new ArrayList<>();
        for (int i=0; i<allTrips.size();i++){
            if (allTrips.get(i).getDestCityId().equals(searchingId)){
                actualTrips.add(allTrips.get(i));
            }
        }
        when(tripRepo.findAllByDestCityId(searchingId)).thenReturn(actualTrips);
        List <Trip> tripsByDestCityId = tripPresentationService.getTripsByDestCity(searchingId);
        assertThat(tripsByDestCityId.size()).isEqualTo(2);
    }

    @Test
    void shouldGetTripsByDestCountryId(){
        int searchingId = 1;
        when(tripRepo.findAll()).thenReturn(allTrips);
        when(cityRepo.findAll()).thenReturn(allCities);
        when(countryRepo.findAll()).thenReturn(allCountries);

        List <City> citiesInCountry = new ArrayList<>();
        for (int i=0; i<allCities.size();i++){
            if (allCities.get(i).getCountryId().equals(searchingId)){
                citiesInCountry.add(allCities.get(i));
            }
        }
        List<Integer> citiesInCountryIds = citiesInCountry.stream()
                .map(c -> Integer.valueOf(c.getId()))
                .collect(Collectors.toList());

        List<Trip> tripsInCountry = new ArrayList<>();
        for(int i=0; i<=citiesInCountryIds.size()-1; i++){
            List<Trip> tripsInCity = tripRepo.findAllByDestCityId(citiesInCountryIds.get(i));
            tripsInCountry.addAll(tripsInCity);

        }


        List <Trip> tripsByDestCountryId = tripPresentationService.getTripsByDestCountry(searchingId);

        assertThat(tripsByDestCountryId.size()).isEqualTo(3);


    }


}