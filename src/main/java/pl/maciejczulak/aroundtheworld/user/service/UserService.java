package pl.maciejczulak.aroundtheworld.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.maciejczulak.aroundtheworld.user.controller.UserController;
import pl.maciejczulak.aroundtheworld.user.model.User;
import pl.maciejczulak.aroundtheworld.user.repository.UserRepo;
import pl.maciejczulak.aroundtheworld.world.model.Airport;

import java.util.List;


@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    public UserRepo repo;
    public PasswordEncoder passwordEncoder;
    public UserService(UserRepo repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsersList() {
        log.info("Getting users list");
        return repo.findAll();
    }

    public User addUser(User user) {
        log.info("Succesfully saved user {} to database", user.getUsername());
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

}
