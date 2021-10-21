package pl.maciejczulak.aroundtheworld.world.repository;

import org.springframework.data.repository.CrudRepository;
import pl.maciejczulak.aroundtheworld.world.model.City;


import java.util.List;
import java.util.Optional;

public interface CityRepo extends CrudRepository<City, Integer> {
    Optional<City> findById(Integer id);
    Optional<City> findByName(String name);
    List<City> findAll();
    }