package com.travelagency.unittest;

import com.travelagency.controllers.UserController;
import com.travelagency.model.Authority;
import com.travelagency.model.AuthorityName;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import com.travelagency.repository.AuthorityRepository;
import com.travelagency.repository.TravelGroupRepository;
import com.travelagency.repository.UserRepository;
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
    private static final String avatar = "avatar-01.png";
    private static final String Token = "userToken";
    private static final Long Id = 131L;

    private final AuthorityRepository authorityRepository = Mockito.mock(AuthorityRepository.class);
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final TravelGroupRepository travelGroupRepository = Mockito.mock(TravelGroupRepository.class);
    private final JwtTokenUtil jwtTokenUtil = Mockito.mock(JwtTokenUtil.class);

    private UserController userController;

    @Before
    public void setUp() {
        userController = new UserController(authorityRepository, userRepository, travelGroupRepository, jwtTokenUtil);
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
        User user = createUser(UserName, Password, FirstName, LastName, EmailAddress, avatar);

        Optional<Long> userId = userController.createUser(user);
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
        User user = createUser(UserName, Password, FirstName, LastName, EmailAddress, avatar);
        Assert.assertFalse(testUpdateUser(null, user));
    }

    @Test
    public void testUpdateUserWithUserNull() {
        Assert.assertFalse(testUpdateUser(Token, null));
    }

    @Test
    public void testUpdateUserWithValidUser() {
        User user = createUser(UserName, Password, FirstName, LastName, EmailAddress, avatar);
        Assert.assertTrue(testUpdateUser(Token, user));
    }

    @Test
    public void testUpdateUserWithUserNameNull() {
        User user = createUser(null, Password, FirstName, LastName, EmailAddress, avatar);
        Assert.assertFalse(testUpdateUser(Token, user));
    }

    @Test
    public void testUpdateUserWithPasswordNull() {
        User user = createUser(UserName, null, FirstName, LastName, EmailAddress, avatar);
        Assert.assertTrue(testUpdateUser(Token, user));
    }

    @Test
    public void testUpdateUserWithFirstNameNull() {
        User user = createUser(UserName, Password, null, LastName, EmailAddress, avatar);
        Assert.assertFalse(testUpdateUser(Token, user));
    }

    @Test
    public void testUpdateUserWithLastNameNull() {
        User user = createUser(UserName, Password, FirstName, null, EmailAddress, avatar);
        Assert.assertFalse(testUpdateUser(Token, user));
    }

    @Test
    public void testUpdateUserWithEmailAddressNull() {
        User user = createUser(UserName, Password, FirstName, LastName, null,avatar);
        Assert.assertFalse(testUpdateUser(Token, user));
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

    @Test
    public void testIsUserAdminWhenAdmin() {
        boolean result = testIsUserAdmin(true);
        Assert.assertTrue(result);
    }

    @Test
    public void testIsUserAdminWhenUser() {
        boolean result = testIsUserAdmin(false);
        Assert.assertFalse(result);
    }

    private boolean testIsUserAdmin(boolean isAdmin) {
        Authority authority = Mockito.mock(Authority.class);
        when(authority.getName()).thenReturn(isAdmin ? AuthorityName.ROLE_ADMIN : AuthorityName.ROLE_USER);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);

        User user = Mockito.mock(User.class);
        when(user.getAuthorities()).thenReturn(authorities);
        String username = "UserName";
        when(userRepository.findByUsername(username)).thenReturn(user);
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn(username);

        return userController.isUserAdmin("token");
    }

    private boolean testUpdateUser(String token, User user) {
        Optional<User> updatedUser = userController.updateUser(token, user);
        return updatedUser.isPresent();
    }

    private Authority getAuthority() {
        return new Authority(AuthorityName.ROLE_USER);
    }

    private User createUser(String userName, String password, String firstName, String lastName, String emailAddress, String avatar) {
        List<Authority> authorities = new ArrayList<>();
        List<TravelGroup> travelGroups = new ArrayList<>();
        authorities.add(getAuthority());

        User user = new User();
        user.setId(Id);
        user.setUsername(userName);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);
        user.setAvatar(avatar);
        user.setEnabled(true);
        user.setAuthorities(authorities);
        user.setTravelGroups(travelGroups);

        return user;
    }

    private User getUser() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityRepository.findByName(AuthorityName.ROLE_USER));

        User user = new User();
        user.setId(Id);
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

