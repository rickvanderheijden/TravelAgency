package com.travelagency;

import com.travelagency.domain.UserCredentials;
import org.springframework.stereotype.Repository;

@Repository
public class UserManager {

    public String test(){
        return "Jaajaaa";
    }

    public String login(UserCredentials userCredentials) {
        return userCredentials.getEmailAddress();
    }

}
