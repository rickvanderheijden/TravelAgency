package com.travelagency;

import com.travelagency.interfaces.TravelAgencyLogin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "com.travelagency")
@EnableJpaRepositories(basePackages = "com.travelagency.repository")
public class BackEndHost implements WebMvcConfigurer {
    public static void main(String[] args)
    {
        // TODO: Create REST Controllers. Which ones? Define interface

        UserManager userManager = new UserManager();
        TravelAgencyLogin travelAgencyLogin = new TravelAgencyLogin(userManager);
        SpringApplication.run(BackEndHost.class, args);

        System.out.println("TravelAgency BackEnd is running");
    }
}
