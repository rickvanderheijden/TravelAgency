package com.travelagency.controllers;

import com.travelagency.model.User;
import com.travelagency.repository.UserRepository;
import com.travelagency.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class JwtController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public JwtController(JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
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
        User user = null;
        String token = request.getHeader(this.tokenHeader).substring(7);
        Optional<User> optionalUser = this.getUserFromToken(token);

        if( !optionalUser.isPresent()){
            return null;
        }
        return optionalUser.get();
    }
}
