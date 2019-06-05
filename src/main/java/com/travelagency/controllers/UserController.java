package com.travelagency.controllers;

import com.travelagency.model.Authority;
import com.travelagency.model.AuthorityName;
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

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

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
        if ((token == null) || (userDTO == null)) return Optional.empty();

        Optional<User> result = Optional.empty();

        if ((userDTO.getUsername() != null) && (userDTO.getEmailAddress() != null) &&
                (userDTO.getFirstName() != null) && (userDTO.getLastName() != null)) {

            String username = jwtTokenUtil.getUsernameFromToken(token);
            User userInDB = userRepository.findByUsername(userDTO.getUsername());

            if ((username != null) && (userInDB != null) &&
                    (username.equals(userDTO.getUsername()) || this.getIsUserAdmin(token))) {
                userInDB.setEmailAddress(userDTO.getEmailAddress());
                userInDB.setFirstName(userDTO.getFirstName());
                userInDB.setLastName(userDTO.getLastName());

                result = Optional.of(userRepository.save(userInDB));
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

    public boolean getIsUserAdmin(String token) {
        boolean isAdmin = false;
        Optional<User> user = getUserFromToken(token);
        if(user.isPresent()) {
            for( Authority authority : user.get().getAuthorities()) {
                if(authority.getName() == AuthorityName.ROLE_ADMIN) {
                    isAdmin = true;
                }
            }
        }
        return isAdmin;
    }

    public List<Authority> getAllAuthorities() {
        return this.authorityRepository.findAll();
    }
}
