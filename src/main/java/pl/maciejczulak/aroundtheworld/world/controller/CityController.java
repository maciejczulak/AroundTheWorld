package pl.maciejczulak.aroundtheworld.world.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.service.CityService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/world/city")
public class CityController {
    private static final Logger log = LoggerFactory.getLogger(CityController.class);
    public CityService cityService;
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public List<City> getCitiesList(){
        log.info("Attempt to get cities list");
        return cityService.getCitiesList();
    }

    @GetMapping("/{id}")
    public Optional<City> getCity(@PathVariable Integer id){
        log.info("Attempt to get city with id={}", id);
        return cityService.getCity(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public City addCity(@RequestBody City city){
        log.info("Attempt to save city {} to database", city.getName());
        return cityService.addCity(city);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public City updateCity(@PathVariable Integer id, @RequestBody City toUpdate){
        log.info("Attempt to update city with id={}", id);
        return cityService.updateCity(id, toUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable Integer id){
        log.info("Attempt to delete city with id={}", id);
        cityService.deleteCity(id);
    }
}
