package com.travelagency.rest;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.travelagency.controllers.UserController;
import com.travelagency.model.Authority;
import com.travelagency.model.AuthorityName;
import com.travelagency.model.User;
import com.travelagency.repository.AuthorityRepository;
import com.travelagency.repository.UserRepository;
import com.travelagency.rest.DataTranfersObjects.UserDTO;
import com.travelagency.security.JwtAuthenticationRequest;
import com.travelagency.security.JwtTokenUtil;
import com.travelagency.security.JwtUser;
import com.travelagency.security.controller.AuthenticationException;
import com.travelagency.security.service.JwtAuthenticationResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationResource {

    @Value("${jwt.header}")
    private String tokenHeader;

    private final UserDetailsService userDetailsService;
    private final AuthorityRepository authorityRepository;
    private final UserController userController;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationResource(
            AuthorityRepository authorityRepository,
            UserController userController,
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService) {
        this.authorityRepository = authorityRepository;
        this.userController = userController;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Long create(@RequestBody final UserDTO userDTO) {

        User user = userDTO.getUser();
        List<Authority> authorities = new ArrayList<>();
        Authority authority = new Authority(AuthorityName.ROLE_USER);
        authorities.add(authority);
        user.setAuthorities(authorities);

        Optional<Long> createdUserId = userController.createUser(user);
        return (createdUserId.orElse(null));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        final String token    = request.getHeader(tokenHeader).substring(7);
        final String username = jwtTokenUtil.getUsernameFromToken(token);
        final JwtUser user    = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate()))
        {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
        else
        {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }


    //TODO: Move to something else?
    public void createUser(
            String username,
            String password,
            String firstName,
            String lastName,
            String emailAddress,
            String avatar,
            AuthorityName authorityName) {

        Authority authority = authorityRepository.findByName(authorityName);
        List<Authority> authorities = Collections.singletonList(authority);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setAuthorities(authorities);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);
        user.setAvatar(avatar);
        user.setEnabled(Boolean.TRUE);
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));

        System.out.println(user.getLastPasswordResetDate().toString());

        userRepository.saveAndFlush(user);
    }


    public void createAuthorities() {
        Authority userRole = new Authority(AuthorityName.ROLE_USER);
        Authority adminRole = new Authority(AuthorityName.ROLE_ADMIN);

        authorityRepository.saveAndFlush(userRole);
        authorityRepository.saveAndFlush(adminRole);
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }

}
