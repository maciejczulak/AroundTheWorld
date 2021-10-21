package pl.maciejczulak.aroundtheworld.world.repository;

import org.springframework.data.repository.CrudRepository;
import pl.maciejczulak.aroundtheworld.world.model.Airport;


import java.util.List;
import java.util.Optional;

public interface AirportRepo extends CrudRepository<Airport, Integer> {
    Optional<Airport> findById(Integer id);
    Optional<Airport> findByName(String name);
    List<Airport> findAll();

}
