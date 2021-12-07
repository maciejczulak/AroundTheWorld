package pl.maciejczulak.aroundtheworld.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.maciejczulak.aroundtheworld.user.model.User;
import pl.maciejczulak.aroundtheworld.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    public UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsersList(){
        log.info("Attempt to get users list");
        return service.getUsersList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User addUser (@RequestBody User user) {
        log.info("Attempt to save user {} to database", user.getUsername());
        return service.addUser(user);
    }
}
