package pl.maciejczulak.aroundtheworld.world.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private static final Logger log = LoggerFactory.getLogger(CityService.class);
    public CityRepo cityRepo;
    public CityService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public List<City> getCitiesList() {
        log.info("Getting cities list");
        return cityRepo.findAll();
    }

    public Optional<City> getCity(Integer id) {
        Optional<City> cityOptional = cityRepo.findById(id);
        if(cityOptional.isEmpty()){
            log.info("City with id={} not found", id); }
        log.info("Getting city with id={}", id);
        return cityOptional;
    }

    public City addCity(City city) {
        log.info("Succesfully saved city {} to database", city.getName());
        return cityRepo.save(city);
    }

    public City updateCity (Integer id, City city){
        Optional<City> cityOptional = cityRepo.findById(id);
        if(cityOptional.isEmpty()){
            log.info("City with id={} not found", id);
            return null;}
        city.setId(id);
        cityRepo.save(city);
        log.info("City with id={} successfully updated", id);
        return city;
    }

    public void deleteCity(Integer id) {
        Optional<City> cityOptional = cityRepo.findById(id);
        cityOptional.ifPresentOrElse(city -> {
                    cityRepo.delete(city);
                    log.info("City with id={} deleted from database", id);
                },
                () -> log.info("City with id={} not found", id)
        );
    }

}
