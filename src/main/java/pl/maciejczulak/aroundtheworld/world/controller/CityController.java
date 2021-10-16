package pl.maciejczulak.aroundtheworld.world.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejczulak.aroundtheworld.world.model.City;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.repository.CityRepo;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/world/city")
public class CityController {
    private static final Logger log = LoggerFactory.getLogger(CityController.class);

    public CityRepo cityRepo;
    public CityController(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    @PostMapping
    ResponseEntity<City> addCity(@RequestBody City toAdd){
        log.info("add city");
        City result = cityRepo.save(toAdd);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }

    @GetMapping
    ResponseEntity<List<City>> readAllCities(){
        log.info("read all cities");
        return ResponseEntity.ok(cityRepo.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<City> readCityById(@PathVariable Integer id) {
        log.info("read city by Id");
        return cityRepo.findById(id)
                .map(city -> ResponseEntity.ok(city))
                .orElse(ResponseEntity.notFound().build());
    }

}
