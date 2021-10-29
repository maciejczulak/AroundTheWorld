package pl.maciejczulak.aroundtheworld.trip.repository;

import org.springframework.data.repository.CrudRepository;
import pl.maciejczulak.aroundtheworld.trip.model.Trip;

import java.util.List;
import java.util.Optional;

public interface TripRepo extends CrudRepository<Trip, Integer> {
    Optional<Trip> findById(Integer id);
    Optional<Trip> findByName(String name);
    List<Trip> findAll();
    List<Trip> findAllByDestCityId(Integer destCityId);
}