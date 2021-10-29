package pl.maciejczulak.aroundtheworld.trip.mapper;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.maciejczulak.aroundtheworld.trip.model.CityDTO;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;
import pl.maciejczulak.aroundtheworld.world.repository.ContinentRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;
import pl.maciejczulak.aroundtheworld.world.service.CityService;
import pl.maciejczulak.aroundtheworld.world.service.ContinentService;
import pl.maciejczulak.aroundtheworld.world.service.CountryService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CityDTOMapperTest {
    final static Country SPAIN = new Country(11, "Spain", 3);
    final static City MADRID = new City(1, "Madrid", 11);

    @Mock
    CountryRepo countryRepo;
    @Mock
    CityRepo cityRepo;

    @InjectMocks
    CityDTOMapper mapper;

    @Test
    void mapEntityToDTOTest() {
        when(countryRepo.findById(11)).thenReturn(Optional.of(SPAIN));
        when(cityRepo.findById(1)).thenReturn(Optional.of(MADRID));
        CityDTO cityDTO = mapper.mapEntityToDTO(MADRID);
        assertThat(cityDTO.getContinentId()).isEqualTo(SPAIN.getContinentId());
    }

    @Test
    void mapDTOToEntityTest() {
        //given
        //final CityDTO madridDTO = new CityDTO(7, "Madrid", 11, 1);
        //when
        //City madrid = mapper.mapDTOToEntity(madridDTO);
        //then
        //assertThat(madrid.getId())
              //  .isEqualTo(7);

    }
}