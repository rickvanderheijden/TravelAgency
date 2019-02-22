package com.travelagency.interfaces;


//TODO (RvdH): Can some methods or constructors be removed?

public class UserCredentials {
    private String emailAddress;
    private String password;

    public UserCredentials(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public UserCredentials() {
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
