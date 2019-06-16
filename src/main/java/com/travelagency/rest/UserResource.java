package com.travelagency.rest;

import com.travelagency.controllers.UserController;
import com.travelagency.model.Authority;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import com.travelagency.rest.DataTranfersObjects.UserDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    private final UserController userController;

    @Value("${jwt.header}")
    private String tokenHeader;

    public UserResource(UserController userController) {
        this.userController = userController;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        return userController.getAllUsers();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Long> create(@RequestBody final UserDTO userDTO) {
        return userController.createUser(userDTO.getUser());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Optional<User> update(HttpServletRequest request, @RequestBody final UserDTO userDTO) {
        String token = request.getHeader(tokenHeader).substring(7);
        return userController.updateUser(token, userDTO.getUser());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        boolean isDeleted = this.userController.deleteUser(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Optional<User> getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        return userController.getUserFromToken(token);
    }

    @RequestMapping(value = "/is-admin", method = RequestMethod.GET)
    public boolean isUserAdmin(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        return userController.isUserAdmin(token);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/getAllAuthorities", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<Authority> getAllAuthorities() {
        return userController.getAllAuthorities();
    }

    @RequestMapping(value = "/travelGroups/{id}", method = RequestMethod.GET)
    public List<TravelGroup> getTravelGroups(@PathVariable final Long id) {
        Optional<User> userOptional = userController.getUserById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            return  userController.getTravelGroups(user);
        }
        return null;
    }

    @RequestMapping(value = "/usernames/{username}", method = RequestMethod.GET)
    public Optional<List<User>> getByUsernameContains(@PathVariable final String username) {
        return userController.getUserByUsernameContains(username);
    }


}
