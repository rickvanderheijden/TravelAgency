package com.travelagency.interfaces;

import com.travelagency.domain.UserCredentials;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TravelAgencyLogin {


    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Jaajaaa";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserCredentials userCredentials) {
        return userCredentials.getEmailAddress();
    }
}
