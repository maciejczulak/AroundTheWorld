package pl.maciejczulak.aroundtheworld.world.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {
    private static final Country EUROPE = new Country(2, "Spain", 1);
    private static final City MADRID = new City (1, "Madrid", 2);

    @Mock
    private CityRepo cityRepo;

    @InjectMocks
    private CityService cityService;

    @Test
    void shouldGetCityById(){
        when(cityRepo.findById(1)).thenReturn(Optional.of(MADRID));
        Optional<City> actualCity = cityService.getCity(1);
        assertThat(actualCity).isEqualTo(Optional.of(MADRID));
    }

    @Test
    void shouldGetCityByCountryId(){
        when(cityRepo.findById(1)).thenReturn(Optional.of(MADRID));

    }


}