package com.travelagency;

import com.travelagency.model.AuthorityName;
import com.travelagency.model.Trip;
import com.travelagency.rest.AuthenticationResource;
import com.travelagency.rest.TripResource;
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
        createTestTrips(context);

        System.out.println("TravelAgency BackEnd is running");
    }

    private static void createTestTrips(ConfigurableApplicationContext context) {
        Trip trip = new Trip(
                "14-DAAGSE RONDREIS HAWAIIAN SPLENDORS",
                "Aloha en welkom op Hawaiiaanse bodem, bekend van rieten rokjes, bloemenkransen en surfers. Natuurlijk is er meer. Per binnenlandse vluchten maak je kennis met de vier grootste eilanden. Relaxen op Waikiki Beach en shoppen in hoofdstad Honolulu op Oahu, actieve vulkanen ontdekken op Big Island, wandelen door tropische landschappen op het groene Kauai en genieten van de zonsondergang op bloemeneiland Maui. Alsof je op ieder eiland in een andere wereld stapt.",
                "https://media.tuicontent.nl/2E3/2E3BCC2E2A88DA5D714748D39B92E24A.jpg",
                1599,
                0);

        context.getBean(TripResource.class).createTrip(trip);

        trip = new Trip(
                "IN DE BAN VAN HET NOORDERLICHT",
                "In het najaar, en tot april, valt het leven op IJsland schijnbaar stil. Zowel toeristen als duizenden schapen verlaten de landschappen en maken plaats voor een serene sfeer die het buitenaards karakter van het eiland nog versterkt. Een ideale periode voor wie houdt van een zekere eenzaamheid die de natuur nu uitstraalt. Geen tristesse, maar wel een ‘grootse’ eenzaamheid met een dramatisch kantje afkomstig van de immer aanwezige woeste natuur. Een uniek gevoel!",
                "https://www.nordic.be/wp-content/uploads/2017/07/IJsland-zuid-geysir-1030x672.jpg",
                1645,
                0);

        context.getBean(TripResource.class).createTrip(trip);
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
