package com.travelagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.travelagency.interfaces")

public class BackEndHost {
    public static void main(String[] args)
    {
        // TODO: Create REST Controllers. Which ones? Define interface

       // TravelAgencyLogin travelAgencyLogin = new TravelAgencyLogin();
        SpringApplication.run(BackEndHost.class, args);

        System.out.println("TravelAgency BackEnd is running");
    }
}
