package pl.maciejczulak.aroundtheworld.trip.mapper;

import org.springframework.stereotype.Component;
import pl.maciejczulak.aroundtheworld.trip.model.CityDTO;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;
import pl.maciejczulak.aroundtheworld.world.service.CountryService;

import java.util.List;

@Component
public class CityDTOMapper {

    public CountryRepo countryRepo;

    public CityDTOMapper(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    public CityDTO mapEntityToDTO(City city) {
        if (city == null) {
            return null;
        }
        Country country = countryRepo.findById(city.getCountryId()).orElseThrow();
        Integer conId = country.getContinentId();

        CityDTO cityDTO = CityDTO.builder()
                .id(city.getId())
                .name(city.getName())
                .countryId(city.getCountryId())
                .continentId(conId)
                .build();
                return cityDTO;
    }

    public City mapDTOToEntity(CityDTO cityDTO) {
        return City.builder()
                .id(cityDTO.getId())
                .name(cityDTO.getName())
                .countryId(cityDTO.getCountryId())
                .build();
    }
}
