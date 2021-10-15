package pl.maciejczulak.aroundtheworld.world.service;

import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.repository.AirportRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;
import pl.maciejczulak.aroundtheworld.world.repository.ContinentRepo;
import pl.maciejczulak.aroundtheworld.world.repository.CountryRepo;
import pl.maciejczulak.aroundtheworld.world.repository.HotelRepo;

@Service
public class WorldService {

    public ContinentRepo continentRepo;
    public CountryRepo countryRepo;
    public CityRepo cityRepo;
    public AirportRepo airportRepo;
    public HotelRepo hotelRepo;

    public WorldService(ContinentRepo continentRepo, CountryRepo countryRepo, CityRepo cityRepo, AirportRepo airportRepo, HotelRepo hotelRepo) {
        this.continentRepo = continentRepo;
        this.countryRepo = countryRepo;
        this.cityRepo = cityRepo;
        this.airportRepo = airportRepo;
        this.hotelRepo = hotelRepo;
    }




}
