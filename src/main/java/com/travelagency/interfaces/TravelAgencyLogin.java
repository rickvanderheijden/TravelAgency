package com.travelagency.interfaces;

import com.travelagency.UserManager;
import com.travelagency.domain.UserCredentials;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TravelAgencyLogin {

    private UserManager userManager;

    public TravelAgencyLogin(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return userManager.test();
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserCredentials userCredentials) {
        return userManager.login(userCredentials);
    }
}
