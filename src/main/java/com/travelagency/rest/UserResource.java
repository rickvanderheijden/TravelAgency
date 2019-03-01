package com.travelagency.rest;

import com.travelagency.model.security.User;
import com.travelagency.security.JwtTokenUtil;
import com.travelagency.security.JwtUser;
import com.travelagency.security.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserRepository userRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> persist(@RequestBody final User user) {
        this.userRepository.save(user);
        return this.userRepository.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser) userDetailsService.loadUserByUsername(username);
    }
}
