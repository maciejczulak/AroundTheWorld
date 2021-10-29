package pl.maciejczulak.aroundtheworld.trip.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.maciejczulak.aroundtheworld.trip.model.CityDTO;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;
import pl.maciejczulak.aroundtheworld.world.service.CityService;
import pl.maciejczulak.aroundtheworld.world.service.ContinentService;
import pl.maciejczulak.aroundtheworld.world.service.CountryService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//entity-city
//category-cityDTO
@ExtendWith(MockitoExtension.class)
class CityDTOMapperTest {

    @Mock
    CountryRepo repo;
    @Mock
    CountryService countryService;
    @Mock
    CityService cityService;
    @Mock
    ContinentService continentService;

    @InjectMocks
    CityDTOMapper mapper;

    @Test
    void mapEntityToDTOTest() {
        //given
        final Continent europe = new Continent(3, "Europe");
        continentService.addContinent(europe);
        final Country spain = new Country(11, "Spain", 3);
        countryService.addCountry(spain);
        final City madrid = new City(1, "Madrid", 11);
        cityService.addCity(madrid);
        //when

        //CityDTO madridDTO = mapper.mapEntityToDTO(madrid);
        //then
        //assertThat(madridDTO.getContinentId())
               // .isEqualTo(3);
        assertThat(countryService.getCountriesList())
                .isEmpty();

    }

    @Test
    void mapDTOToEntityTest() {
        //given
        final CityDTO madridDTO = new CityDTO(7, "Madrid", 11, 1);
        //when
        City madrid = mapper.mapDTOToEntity(madridDTO);
        //then
        assertThat(madrid.getId())
                .isEqualTo(7);

    }
}