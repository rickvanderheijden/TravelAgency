package com.travelagency.rest;

import com.travelagency.model.Authority;
import com.travelagency.model.User;
import com.travelagency.repository.AuthorityRepository;
import com.travelagency.rest.DataTranfersObjects.UserDTO;
import com.travelagency.security.JwtTokenUtil;
import com.travelagency.security.JwtUser;
import com.travelagency.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

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

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public Long create(@RequestBody final UserDTO userDTO) {

        User user = userDTO.getUser();

        List<Authority> authorities = new ArrayList<>();
        for (Authority authority : userDTO.getAuthorities()) {
            authorities.add(authorityRepository.findByName(authority.getName()));
        }

        user.setAuthorities(authorities);
        User createdUser = userRepository.save(user);
        return createdUser.getId();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<JwtUser> getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);

        return new ResponseEntity<>((JwtUser) userDetailsService.loadUserByUsername(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> getById(@PathVariable final Long id) {
        return this.userRepository.findById(id);
    }

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> getByUsername(@PathVariable final String username) {
        return Optional.ofNullable(this.userRepository.findByUsername(username));
    }

    @RequestMapping(value = "/email/{emailAddress}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> getByEmailAddress(@PathVariable final String emailAddress) {
        return Optional.ofNullable(this.userRepository.findByEmailAddress(emailAddress));
    }
}
