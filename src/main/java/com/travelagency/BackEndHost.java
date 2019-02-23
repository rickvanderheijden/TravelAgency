package com.travelagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = "com.travelagency.interfaces")
@EnableJpaRepositories(basePackages = "com.travelagency.repository")
public class BackEndHost {
    public static void main(String[] args)
    {
        // TODO: Create REST Controllers. Which ones? Define interface

       // TravelAgencyLogin travelAgencyLogin = new TravelAgencyLogin();
        SpringApplication.run(BackEndHost.class, args);

        System.out.println("TravelAgency BackEnd is running");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void	addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:4200");
            }
        };
    }
}
