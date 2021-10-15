package pl.maciejczulak.aroundtheworld.world.repository;

import org.springframework.data.repository.CrudRepository;
import pl.maciejczulak.aroundtheworld.world.model.Continent;
import pl.maciejczulak.aroundtheworld.world.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryRepo extends CrudRepository<Country, Integer> {
    Optional<Country> findById(Integer id);
    Optional<Country> findByName(String name);
    List<Country> findAll();
    List<Country> findAllByContinentId(Integer continentId);
    }
