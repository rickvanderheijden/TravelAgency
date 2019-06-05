package com.travelagency.unittest;

import com.travelagency.controllers.UserController;
import com.travelagency.model.Authority;
import com.travelagency.model.AuthorityName;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import com.travelagency.repository.AuthorityRepository;
import com.travelagency.repository.TravelGroupRepository;
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

    private static final String UserName = "username";
    private static final String Password = "password";
    private static final String FirstName = "firstName";
    private static final String LastName = "lastName";
    private static final String EmailAddress = "emailAddress";
    private static final String Token = "usertoken";
    private static final Long Id = 131L;

    private final AuthorityRepository authorityRepository = Mockito.mock(AuthorityRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final JwtTokenUtil jwtTokenUtil = Mockito.mock(JwtTokenUtil.class);
    private final TravelGroupRepository travelGroupRepository = Mockito.mock(TravelGroupRepository.class);

    private UserController userController;

    @Before
    public void setUp() {
        userController = new UserController(authorityRepository, userRepository, jwtTokenUtil, travelGroupRepository);
        when(authorityRepository.findByName(AuthorityName.ROLE_USER)).thenReturn(getAuthority());
        when(authorityRepository.findByName(AuthorityName.ROLE_ADMIN)).thenReturn(getAuthority());

        User user = getUser();
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userRepository.findByUsername(UserName)).thenReturn(user);
        when(userRepository.findByEmailAddress(EmailAddress)).thenReturn(user);
        when(userRepository.findById(Id)).thenReturn(Optional.of(user));
        when(jwtTokenUtil.getUsernameFromToken(Token)).thenReturn(UserName);

    }

    @After
    public void tearDown() {
        userController = null;
    }

    @Test
    public void testGetUserByUserNameWithExistingUserName() {
        Optional<User> user = userController.getUserByUsername(UserName);
        Assert.assertTrue(UserName, user.isPresent());
        Assert.assertEquals(UserName, user.get().getUsername());
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
        UserDTO userDTO = createUserDTO(UserName, Password, FirstName, LastName, EmailAddress);

        Optional<Long> userId = userController.createUser(userDTO);
        Assert.assertTrue(userId.isPresent());
        Assert.assertNotNull(userId.get());
    }

    @Test
    public void testCreateUserWithNull() {
        Optional<Long> userId = userController.createUser(null);
        Assert.assertFalse(userId.isPresent());
    }

    @Test
    public void testUpdateUserWithTokenNull() {
        UserDTO userDTO = createUserDTO(UserName, Password, FirstName, LastName, EmailAddress);
        Assert.assertFalse(testUpdateUser(null, userDTO));
    }

    @Test
    public void testUpdateUserWithUserNull() {
        Assert.assertFalse(testUpdateUser(Token, null));
    }

    @Test
    public void testUpdateUserWithValidUser() {
        UserDTO userDTO = createUserDTO(UserName, Password, FirstName, LastName, EmailAddress);
        Assert.assertTrue(testUpdateUser(Token, userDTO));
    }

    @Test
    public void testUpdateUserWithUserNameNull() {
        UserDTO userDTO = createUserDTO(null, Password, FirstName, LastName, EmailAddress);
        Assert.assertFalse(testUpdateUser(Token, userDTO));
    }

    @Test
    public void testUpdateUserWithPasswordNull() {
        UserDTO userDTO = createUserDTO(UserName, null, FirstName, LastName, EmailAddress);
        Assert.assertTrue(testUpdateUser(Token, userDTO));
    }

    @Test
    public void testUpdateUserWithFirstNameNull() {
        UserDTO userDTO = createUserDTO(UserName, Password, null, LastName, EmailAddress);
        Assert.assertFalse(testUpdateUser(Token, userDTO));
    }

    @Test
    public void testUpdateUserWithLastNameNull() {
        UserDTO userDTO = createUserDTO(UserName, Password, FirstName, null, EmailAddress);
        Assert.assertFalse(testUpdateUser(Token, userDTO));
    }

    @Test
    public void testUpdateUserWithEmailAddressNull() {
        UserDTO userDTO = createUserDTO(UserName, Password, FirstName, LastName, null);
        Assert.assertFalse(testUpdateUser(Token, userDTO));
    }

    @Test
    public void testGetUserFromEmailAddress() {
        Optional<User> user = userController.getUserByEmailAddress(EmailAddress);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void testGetUserFromEmailAddressInvalidEmailAddress() {
        Optional<User> user = userController.getUserByEmailAddress("invalidEmailAddress");
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void testGetUserFromEmailAddressNull() {
        Optional<User> user = userController.getUserByEmailAddress(null);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void testGetUserFromToken() {
        Optional<User> user = userController.getUserFromToken(Token);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void testGetUserFromTokenInvalidToken() {
        Optional<User> user = userController.getUserFromToken("invalidToken");
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void testGetUserFromTokenNull() {
        Optional<User> user = userController.getUserFromToken(null);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void testGetUserById() {
        Optional<User> user = userController.getUserById(Id);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void testGetUserByIdInvalidId() {
        Optional<User> user = userController.getUserById(-1L);
        Assert.assertFalse(user.isPresent());
    }

    @Test
    public void testGetUserByIdNull() {
        Optional<User> user = userController.getUserById(null);
        Assert.assertFalse(user.isPresent());
    }

    private boolean testUpdateUser(String token, UserDTO userDTO) {
        Optional<User> user = userController.updateUser(token, userDTO);
        return user.isPresent();
    }

    private Authority getAuthority() {
        return new Authority(AuthorityName.ROLE_USER);
    }

    private UserDTO createUserDTO(String userName, String password, String firstName, String lastName, String emailAddress) {
        List<Authority> authorities = new ArrayList<>();
        List<TravelGroup> travelGroups = new ArrayList<>();
        authorities.add(getAuthority());
        return new UserDTO(userName, password, firstName, lastName, emailAddress, true, authorities, travelGroups);
    }

    private User getUser() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityRepository.findByName(AuthorityName.ROLE_USER));

        User user = new User();
        user.setId(1L);
        user.setUsername(UserName);
        user.setPassword(bCryptPasswordEncoder.encode(Password));
        user.setAuthorities(authorities);
        user.setFirstName(FirstName);
        user.setLastName(LastName);
        user.setEmailAddress(EmailAddress);
        user.setEnabled(Boolean.TRUE);
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));

        return user;
    }
}

