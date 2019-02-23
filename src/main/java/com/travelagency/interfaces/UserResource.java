package com.travelagency.interfaces;

import com.travelagency.domain.User;
import com.travelagency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/all")
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @PostMapping(value = "/save")
    public List<User> persist(@RequestBody final User user) {
        this.userRepository.save(user);
        return this.userRepository.findAll();
    }
}
