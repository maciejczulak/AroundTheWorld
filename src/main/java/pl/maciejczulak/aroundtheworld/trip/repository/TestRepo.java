package pl.maciejczulak.aroundtheworld.trip.repository;

import org.springframework.data.repository.CrudRepository;
import pl.maciejczulak.aroundtheworld.trip.model.Test;
import pl.maciejczulak.aroundtheworld.world.model.Airport;

import java.util.List;
import java.util.Optional;

public interface TestRepo extends CrudRepository<Test, Integer> {
    Optional<Test> findById(Integer id);
        List<Test> findAll();
    }
