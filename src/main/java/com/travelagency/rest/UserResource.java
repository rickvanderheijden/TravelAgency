package com.travelagency.rest;

import com.travelagency.controllers.UserController;
import com.travelagency.model.User;
import com.travelagency.rest.DataTranfersObjects.UserDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserController userController;

    @Value("${jwt.header}")
    private String tokenHeader;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAll() {
        return userController.getAllUsers();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Long> create(@RequestBody final UserDTO userDTO) {
        return userController.createUser(userDTO);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Optional<User> update(HttpServletRequest request, @RequestBody final UserDTO userDTO) {
        String token = request.getHeader(tokenHeader).substring(7);
        return userController.updateUser(token, userDTO);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Optional<User> getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        return userController.getUserFromToken(token);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> getById(@PathVariable final Long id) {
        return userController.getUserById(id);
    }

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> getByUsername(@PathVariable final String username) {
        return userController.getUserByUsername(username);
    }

    @RequestMapping(value = "/email/{emailAddress}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> getByEmailAddress(@PathVariable final String emailAddress) {
        return userController.getUserByEmailAddress(emailAddress);
    }
}
