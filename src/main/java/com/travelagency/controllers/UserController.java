package com.travelagency.controllers;

import com.travelagency.model.Authority;
import com.travelagency.model.AuthorityName;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import com.travelagency.repository.AuthorityRepository;
import com.travelagency.repository.TravelGroupRepository;
import com.travelagency.repository.UserRepository;
import com.travelagency.security.JwtTokenUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final TravelGroupRepository travelGroupRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(AuthorityRepository authorityRepository, UserRepository userRepository,
                          TravelGroupRepository travelGroupRepository, JwtTokenUtil jwtTokenUtil) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.travelGroupRepository = travelGroupRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public Optional<Long> createUser(User user) {
        if (user == null) return Optional.empty();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        List<Authority> authorities = new ArrayList<>();
        for (Authority authority : user.getAuthorities()) {
            authorities.add(authorityRepository.findByName(authority.getName()));
        }

        user.setAuthorities(authorities);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        User createdUser = userRepository.save(user);
        return Optional.ofNullable(createdUser.getId());
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> updateUser(String token, User user) {
        if ((token == null) || (user == null)) return Optional.empty();

        Optional<User> result = Optional.empty();

        if (user.isValid()) {
            String currentUsername = jwtTokenUtil.getUsernameFromToken(token);
            User currentUser = userRepository.findByUsername(currentUsername);
            Optional<User> userInDB = userRepository.findById(user.getId());

            if (userInDB.isPresent() && (currentUser.getId().equals(user.getId()) || this.isUserAdmin(token))) {
                userInDB.get().setEmailAddress(user.getEmailAddress());
                userInDB.get().setFirstName(user.getFirstName());
                userInDB.get().setLastName(user.getLastName());
                userInDB.get().setAvatar(user.getAvatar());

                result = Optional.of(userRepository.save(userInDB.get()));
            }
        }

        return result;
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

    public boolean isUserAdmin(String token) {
        boolean isAdmin = false;
        Optional<User> user = getUserFromToken(token);
        if (user.isPresent()) {
            for (Authority authority : user.get().getAuthorities()) {
                if (authority.getName() == AuthorityName.ROLE_ADMIN) {
                    isAdmin = true;
                }
            }
        }
        return isAdmin;
    }

    public List<Authority> getAllAuthorities() {
        return this.authorityRepository.findAll();
    }

    public List<TravelGroup> getTravelGroups(User user) {
        return this.travelGroupRepository.findByUsers(user);
    }

    public boolean addTravelGroup(TravelGroup travelGroup, Long id) {
        Optional<User> user = getUserById(id);
        if (user.isPresent()) {
            user.get().addTravelGroup(travelGroupRepository.findById(travelGroup.getId()).get());
            userRepository.save(user.get());
        }
        return true;
    }

    public boolean removeTravelGroup(TravelGroup travelGroup) {
        Optional<List<User>> users = getUserByTravelGroup(travelGroup);
        if (users.isPresent()) {
            for (User user : users.get()) {
                user.removeTravelGroup(travelGroupRepository.findById(travelGroup.getId()).get());
                userRepository.save(user);
            }
        }
        return true;
    }

    public Optional<List<User>> getUserByTravelGroup(TravelGroup travelGroup) {
        return Optional.ofNullable(userRepository.findByTravelGroups(travelGroup));
    }

    public Optional<List<User>> getUserByUsernameContains(String username) {
        return Optional.ofNullable(userRepository.findByUsernameContains(username));
    }

    public boolean deleteUser(Long id) {
        boolean doesExist = userRepository.existsById(id);
        if (!doesExist) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}
