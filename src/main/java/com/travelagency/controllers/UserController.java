package com.travelagency.controllers;

import com.travelagency.model.Authority;
import com.travelagency.model.User;
import com.travelagency.repository.AuthorityRepository;
import com.travelagency.repository.UserRepository;
import com.travelagency.rest.DataTranfersObjects.UserDTO;
import com.travelagency.security.JwtTokenUtil;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private AuthorityRepository authorityRepository;
    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;

    public UserController(AuthorityRepository authorityRepository, UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public Optional<Long> createUser(UserDTO userDTO) {
        if (userDTO == null) return Optional.empty();

        User user = userDTO.getUser();

        List<Authority> authorities = new ArrayList<>();
        for (Authority authority : userDTO.getAuthorities()) {
            authorities.add(authorityRepository.findByName(authority.getName()));
        }

        user.setAuthorities(authorities);
        User createdUser = userRepository.save(user);
        return Optional.ofNullable(createdUser.getId());
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> updateUser(String token, UserDTO userDTO) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User userInDB = userRepository.findByUsername(userDTO.getUsername());

        if (username.equals(userDTO.getUsername())) {
            userInDB.setEmailAddress(userDTO.getEmailAddress());
            userInDB.setFirstname(userDTO.getFirstname());
            userInDB.setLastname(userDTO.getLastname());
        }

        return Optional.of(userRepository.save(userInDB));
    }

    public Optional<User> getUserById(Long id) {
        return this.userRepository.findById(id);
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public Optional<User> getUserByEmailAddress(String emailAddress) {
        return Optional.ofNullable(userRepository.findByEmailAddress(emailAddress));
    }

    public Optional<User> getUserFromToken(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return getUserByUsername(username);
    }
}
