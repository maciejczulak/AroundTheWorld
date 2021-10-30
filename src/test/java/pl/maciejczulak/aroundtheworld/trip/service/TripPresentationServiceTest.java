package pl.maciejczulak.aroundtheworld.trip.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.maciejczulak.aroundtheworld.trip.mapper.TripDTOMapper;
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
    List <Country> ALL_COUNTRIES = List.of(COUNTRY1, COUNTRY2);

    final static City CITY1 = new City(1, "Paris", 1);
    final static City CITY2 = new City(2, "Lyon", 1);
    final static City CITY3 = new City(3, "Nice", 1);
    final static City CITY4 = new City(4, "Madrid", 2);
    final static City CITY5 = new City(5, "Barcelona", 2);
    final static City CITY6 = new City(6, "Sevilla", 2);
    List <City> ALL_CITIES = List.of(CITY1, CITY2, CITY3, CITY4, CITY5, CITY6);

    final static LocalDate INCOMING_DATE = LocalDate.of(2021, 11, 6);
    final static Trip TRIP1 = new Trip(1, "Francja-elegancja", 4, null, 1, null, null, INCOMING_DATE, null, null, null, null, true);
    final static Trip TRIP2 = new Trip(2, "Francja-elokwencja", 4, null, 1, null, null, INCOMING_DATE, null, null, null, null, true);
    final static Trip TRIP3 = new Trip(3, "Słoneczna hiszpania", 1, null, 5, null, null, INCOMING_DATE, null, null, null, null, false);
    List <Trip> ALL_TRIPS = List.of(TRIP1, TRIP2, TRIP3);

    @Mock
    private ContinentRepo continentRepo;
    @Mock
    private CountryRepo countryRepo;
    @Mock
    private CityRepo cityRepo;
    @Mock
    private TripRepo tripRepo;
    @Mock
    private TripDTOMapper mapper;

    @InjectMocks
    private TripPresentationService tripPresentationService;

    @Test
    void shouldGetPromotedTrips() {
        when(tripRepo.findAll()).thenReturn(ALL_TRIPS);
        List<Trip> promotedTrips = tripPresentationService.getPromotedTrips();
        assertThat(promotedTrips.size()).isEqualTo(2);
        }

    @Test
    void shouldGetIncomingTrips(){
        when(tripRepo.findAll()).thenReturn(ALL_TRIPS);
        List<Trip> incomingTrips = tripPresentationService.getIncomingTrips();
        assertThat(incomingTrips.size()).isEqualTo(3);
    }

    @Test
    void shouldGetTripsByDestCityId(){
        int searchingId = 1;
        when(tripRepo.findAll()).thenReturn(ALL_TRIPS);
        List <Trip> actualTrips = new ArrayList<>();
        for (int i=0; i<ALL_TRIPS.size();i++){
            if (ALL_TRIPS.get(i).getDestCityId().equals(searchingId)){
                actualTrips.add(ALL_TRIPS.get(i));
            }
        }
        when(tripRepo.findAllByDestCityId(searchingId)).thenReturn(actualTrips);

        List <Trip> tripsByDestCityId = tripPresentationService.getTripsByDestCity(searchingId);
        assertThat(tripsByDestCityId.size()).isEqualTo(2);
    }

    @Test
    void shouldGetTripsByDestCountryId(){
        int searchingId = 1;
        when(tripRepo.findAll()).thenReturn(ALL_TRIPS);
        when(cityRepo.findById(CITY1.getId())).thenReturn(Optional.of(CITY1));
        when(cityRepo.findById(CITY2.getId())).thenReturn(Optional.of(CITY2));
        when(cityRepo.findById(CITY3.getId())).thenReturn(Optional.of(CITY3));
        when(cityRepo.findById(CITY4.getId())).thenReturn(Optional.of(CITY4));
        when(cityRepo.findById(CITY5.getId())).thenReturn(Optional.of(CITY5));
        when(cityRepo.findById(CITY6.getId())).thenReturn(Optional.of(CITY6));
        when(countryRepo.findById(COUNTRY1.getId())).thenReturn(Optional.of(COUNTRY1));
        when(countryRepo.findById(COUNTRY2.getId())).thenReturn(Optional.of(COUNTRY2));


        List<Trip> tripsByDestCountryId = tripPresentationService.getTripsByDestCountry(searchingId);

        assertThat(tripsByDestCountryId.size()).isEqualTo(3);


   }


}