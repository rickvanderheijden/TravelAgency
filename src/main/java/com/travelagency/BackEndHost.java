package com.travelagency;

import com.travelagency.model.AuthorityName;
import com.travelagency.rest.AuthenticationResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = "com.travelagency")
@EnableJpaRepositories(basePackages = "com.travelagency.repository")
public class BackEndHost implements WebMvcConfigurer {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BackEndHost.class, args);

        //TODO: Remove when done testing
        createTestUsers(context);
        createTestTrips();

        System.out.println("TravelAgency BackEnd is running");
    }

    private static void createTestTrips() {

    }

    private static void createTestUsers(ConfigurableApplicationContext context) {
        context.getBean(AuthenticationResource.class).createAuthorities();
        context.getBean(AuthenticationResource.class).createUser(
                "user",
                "user",
                "userFirstName",
                "userLastName",
                "user@travelagency.com",
                AuthorityName.ROLE_USER);

        context.getBean(AuthenticationResource.class).createUser(
                "admin",
                "admin",
                "adminFirstName",
                "adminLastName",
                "admin@travelagency.com",
                AuthorityName.ROLE_ADMIN);
    }

    @Override
    public void	addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:4200");
    }
}
