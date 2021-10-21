package pl.maciejczulak.aroundtheworld.world.repository;

import org.springframework.data.repository.CrudRepository;

import pl.maciejczulak.aroundtheworld.world.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelRepo extends CrudRepository<Hotel, Integer> {
    Optional<Hotel> findById(Integer id);
    Optional<Hotel> findByName(String name);
    List<Hotel> findAll();
    }
