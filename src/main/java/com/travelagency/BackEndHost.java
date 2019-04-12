package com.travelagency;

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
        TestDataCreator testDataCreator = new TestDataCreator();
        testDataCreator.setContext(context);
        testDataCreator.createTestData();

        System.out.println("TravelAgency BackEnd is running");
    }

    @Override
    public void	addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:4200");
    }
}
