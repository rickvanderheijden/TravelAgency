package com.travelagency.unittest;

import com.travelagency.controllers.UserController;
import com.travelagency.model.Authority;
import com.travelagency.model.AuthorityName;
import com.travelagency.model.User;
import com.travelagency.repository.AuthorityRepository;
import com.travelagency.repository.UserRepository;
import com.travelagency.rest.DataTranfersObjects.UserDTO;
import com.travelagency.security.JwtTokenUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestUserController {

    final AuthorityRepository authorityRepository = Mockito.mock(AuthorityRepository.class);
    final UserRepository userRepository = Mockito.mock(UserRepository.class);
    final JwtTokenUtil jwtTokenUtil = Mockito.mock(JwtTokenUtil.class);

    private UserController userController;

    @Before
    public void setUp() {
        userController = new UserController(authorityRepository, userRepository, jwtTokenUtil);
        when(authorityRepository.findByName(AuthorityName.ROLE_USER)).thenReturn(getAuthority(AuthorityName.ROLE_USER));
        when(authorityRepository.findByName(AuthorityName.ROLE_ADMIN)).thenReturn(getAuthority(AuthorityName.ROLE_USER));

        User user = getUser();
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findByUsername("username")).thenReturn(user);
    }

    @After
    public void tearDown() {
        userController = null;
    }

    @Test
    public void testGetUserByUserNameWithExistingUserName() {
        Optional<User> user = userController.getUserByUsername("username");
        Assert.assertTrue("username", user.isPresent());
        Assert.assertEquals("username", user.get().getUsername());
    }

    @Test
    public void testGetUserByUserNameWithInvalidUserName() {
        Optional<User> user = userController.getUserByUsername("invalid");
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void testGetUserByUserNameWithNull() {
        Optional<User> user = userController.getUserByUsername(null);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void testCreateUserThatDoesNotExist() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(getAuthority(AuthorityName.ROLE_USER));
        UserDTO userDTO = new UserDTO("username", "password", "firstname", "lastname", "emailAddress", true, authorities);

        Optional<Long> userId = userController.createUser(userDTO);
        Assert.assertTrue(userId.isPresent());
        Assert.assertNotNull(userId.get());
    }

    @Test
    public void testCreateUserWithNull() {
        Optional<Long> userId = userController.createUser(null);
        Assert.assertFalse(userId.isPresent());
    }

    private Authority getAuthority(AuthorityName authorityName) {
        return new Authority(authorityName);
    }

    private User getUser() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityRepository.findByName(AuthorityName.ROLE_USER));

        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword(bCryptPasswordEncoder.encode("password"));
        user.setAuthorities(authorities);
        user.setFirstname("firstName");
        user.setLastname("lastName");
        user.setEmailAddress("emailAddress");
        user.setEnabled(Boolean.TRUE);
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));

        return user;
    }
}

