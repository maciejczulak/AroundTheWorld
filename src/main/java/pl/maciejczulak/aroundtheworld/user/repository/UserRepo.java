package pl.maciejczulak.aroundtheworld.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.maciejczulak.aroundtheworld.user.model.User;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findAll();
}
