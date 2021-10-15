package pl.maciejczulak.aroundtheworld.world.repository;

import org.springframework.data.repository.CrudRepository;
import pl.maciejczulak.aroundtheworld.world.model.Continent;

import java.util.List;
import java.util.Optional;

public interface ContinentRepo extends CrudRepository<Continent, Integer> {
    Optional<Continent> findById(Integer id);
    Optional<Continent> findByName(String name);
    List<Continent> findAll();
}
