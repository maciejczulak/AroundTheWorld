package pl.maciejczulak.aroundtheworld.trip.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.maciejczulak.aroundtheworld.trip.model.CityDTO;
import pl.maciejczulak.aroundtheworld.trip.model.Trip;
import pl.maciejczulak.aroundtheworld.trip.model.TripDTO;
import pl.maciejczulak.aroundtheworld.trip.repository.TripRepo;
import pl.maciejczulak.aroundtheworld.trip.service.TripPresentationService;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;
import pl.maciejczulak.aroundtheworld.world.repository.ContinentRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TripDTOMapperTest {
    final static Continent START_CONTINENT = new Continent(1, "Asia");
    final static Continent DEST_CONTINENT = new Continent(2, "Europe");
    final static Country START_COUNTRY  = new Country(23, "Japan", 1);
    final static Country DEST_COUNTRY = new Country(11, "France", 2);
    final static City START_CITY = new City(3, "Tokyo", 23);
    final static City DEST_CITY = new City(5, "Paris", 11);
    final static Trip TRIP1 = new Trip(7, "Mapper Test Trip", 3, null, 5, null, null, null, null, null, null, null, true);

    @Mock
    private CountryRepo countryRepo;
    @Mock
    private CityRepo cityRepo;
    @Mock
    private TripRepo tripRepo;

    @InjectMocks
    private TripDTOMapper mapper;

    @Test
    void shouldMapTripToDTO() {
        when(tripRepo.findById(TRIP1.getId())).thenReturn(Optional.of(TRIP1));
        when(cityRepo.findById(START_CITY.getId())).thenReturn(Optional.of(START_CITY));
        when(cityRepo.findById(DEST_CITY.getId())).thenReturn(Optional.of(DEST_CITY));
        when(countryRepo.findById(START_COUNTRY.getId())).thenReturn(Optional.of(START_COUNTRY));
        when(countryRepo.findById(DEST_COUNTRY.getId())).thenReturn(Optional.of(DEST_COUNTRY));

        TripDTO testedTrip = mapper.mapTripToDTO(TRIP1);

        assertThat(testedTrip.getDestCityId()).isEqualTo(DEST_CITY.getId());
        assertThat(testedTrip.getDestCountryId()).isEqualTo(DEST_COUNTRY.getId());
        assertThat(testedTrip.getDestContinentId()).isEqualTo(DEST_CONTINENT.getId());
        assertThat(testedTrip.getStartCityId()).isEqualTo(START_CITY.getId());
        assertThat(testedTrip.getStartCountryId()).isEqualTo(START_COUNTRY.getId());
        assertThat(testedTrip.getStartContinentId()).isEqualTo(START_CONTINENT.getId());
    }
    @Test
    void mapDTOtoTrip() {
        when(tripRepo.findById(TRIP1.getId())).thenReturn(Optional.of(TRIP1));
        TripDTO tripDTO1 = new TripDTO(TRIP1.getId(), null, null, null, null, null, null);
        Trip testedTrip = mapper.mapDTOtoTrip(tripDTO1);
        assertThat(testedTrip.getPromoted()).isEqualTo(true);
    }
}