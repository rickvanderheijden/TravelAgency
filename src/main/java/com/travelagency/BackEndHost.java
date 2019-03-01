package com.travelagency;

import com.travelagency.model.security.AuthorityName;
import com.travelagency.rest.Authentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SuppressWarnings("SpellCheckingInspection")
@SpringBootApplication(scanBasePackages = "com.travelagency")
@EnableJpaRepositories(basePackages = "com.travelagency.security.repository")
public class BackEndHost implements WebMvcConfigurer {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BackEndHost.class, args);

        //TODO: Remove when done testing
        createTestUsers(context);

        System.out.println("TravelAgency BackEnd is running");
    }

    private static void createTestUsers(ConfigurableApplicationContext context) {
        context.getBean(Authentication.class).createAuthorities();
        context.getBean(Authentication.class).createUser(
                "user",
                "user",
                "userFirstName",
                "userLastName",
                "user@travelagency.com",
                AuthorityName.ROLE_USER);

        context.getBean(Authentication.class).createUser(
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
