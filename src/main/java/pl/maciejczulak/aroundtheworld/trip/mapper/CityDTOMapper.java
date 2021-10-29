package pl.maciejczulak.aroundtheworld.trip.mapper;

import org.springframework.stereotype.Component;
import pl.maciejczulak.aroundtheworld.trip.model.CityDTO;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.service.CountryService;

import java.util.List;

@Component
public class CityDTOMapper {

    public CountryService countryService;
    public CityDTOMapper(CountryService countryService) {
        this.countryService = countryService;
    }

    public CityDTO mapEntityToDTO(City city) {
        if (city == null) {
            return null;
        }

        List<Country> l = countryService.getCountriesList();
        Integer conId=0;
        for (int i=0; i<=l.size(); i++) {
            if (l.get(i).equals(city.getCountryId())){
                conId=l.get(i).getContinentId();
            }
        }

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
