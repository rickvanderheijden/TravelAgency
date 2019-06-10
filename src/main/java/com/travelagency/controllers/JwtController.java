package com.travelagency.controllers;

import com.travelagency.model.User;
import com.travelagency.repository.UserRepository;
import com.travelagency.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class JwtController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    public JwtController(JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    public Optional<User> getUserFromToken(String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return this.getUserByUsername(username);
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public User userExists(HttpServletRequest request){
        String token = request.getHeader(this.tokenHeader).substring(7);
        Optional<User> optionalUser = this.getUserFromToken(token);

        return optionalUser.orElse(null);
    }
}
