package com.travelagency.rest.DataTranfersObjects;
import com.travelagency.model.Authority;
import com.travelagency.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO {

    private final String username;
    private final String password;
    private final String firstname;
    private final String lastname;
    private final String emailAddress;
    private final Boolean enabled;
    private final List<Authority> authorities;

    public UserDTO(String username, String password, String firstname, String lastname, String emailAddress, Boolean enabled, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public UserDTO(String username, String password, String firstname, String lastname, String emailAddress) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.enabled = true;
        this.authorities = new ArrayList<Authority>();
    }

    public UserDTO() {
        this.username = "";
        this.password = "";
        this.firstname = "";
        this.lastname = "";
        this.emailAddress = "";
        this.enabled = true;
        this.authorities = new ArrayList<Authority>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public User getUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmailAddress(emailAddress);
        user.setEnabled(enabled);
        user.setAuthorities(authorities);
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));

        return user;
    }
}
