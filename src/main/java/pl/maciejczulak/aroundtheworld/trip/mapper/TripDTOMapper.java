package pl.maciejczulak.aroundtheworld.trip.mapper;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pl.maciejczulak.aroundtheworld.trip.model.Trip;
import pl.maciejczulak.aroundtheworld.trip.model.TripDTO;
import pl.maciejczulak.aroundtheworld.trip.repository.TripRepo;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Country;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;

import java.util.List;
import java.util.Optional;

@Component
public class TripDTOMapper {
    private CityRepo cityRepo;
    private CountryRepo countryRepo;
    private TripRepo tripRepo;

    public TripDTOMapper(CityRepo cityRepo, CountryRepo countryRepo, TripRepo tripRepo) {
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
        this.tripRepo = tripRepo;
    }

    public TripDTO mapTripToDTO (Trip trip){

        City startCity = cityRepo.findById(trip.getStartCityId()).orElseThrow();
        Country startCountry = countryRepo.findById(startCity.getCountryId()).orElseThrow();
        City destCity = cityRepo.findById(trip.getDestCityId()).orElseThrow();
        Country destCountry = countryRepo.findById(destCity.getCountryId()).orElseThrow();

        TripDTO tripDTO = TripDTO.builder()
                .id(trip.getId())
                .startCityId(trip.getStartCityId())
                .startCountryId(startCity.getCountryId())
                .startContinentId(startCountry.getContinentId())
                .destCityId(trip.getDestCityId())
                .destCountryId(destCity.getCountryId())
                .destContinentId(destCountry.getContinentId())
                .build();
        return tripDTO;
    }

    public Trip mapDTOtoTrip (TripDTO tripDTO){
        return tripRepo.findById(tripDTO.getId()).orElseThrow();
    }




}
