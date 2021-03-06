package com.travelagency.rest.DataTranfersObjects;
import com.travelagency.model.Authority;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO {

    private final Long id;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String avatar;
    private final Boolean enabled;
    private final List<Authority> authorities;
    private final List<TravelGroup> travelGroups;

    public UserDTO(Long id, String username, String password, String firstName, String lastName, String emailAddress, String avatar, Boolean enabled, List<Authority> authorities, List<TravelGroup> travelGroups) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.avatar = avatar;
        this.enabled = enabled;
        this.authorities = authorities;
        this.travelGroups = travelGroups;
    }

    public UserDTO(Long id, String username, String password, String firstName, String lastName, String emailAddress, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.avatar = avatar;
        this.enabled = true;
        this.authorities = new ArrayList<>();
        this.travelGroups = new ArrayList<>();
    }

    public UserDTO() {
        this.id = null;
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.emailAddress = "";
        this.avatar = "";
        this.enabled = true;
        this.authorities = new ArrayList<>();
        this.travelGroups = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public List<TravelGroup> getTravelGroups() {
        return travelGroups;
    }

    public String getAvatar() {
        return avatar;
    }

    public User getUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAddress(emailAddress);
        user.setAvatar(avatar);
        user.setEnabled(enabled);
        user.setAuthorities(authorities);
        user.setTravelGroups(travelGroups);
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));

        return user;
    }
}
