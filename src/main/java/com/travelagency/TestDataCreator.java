package com.travelagency;

import com.travelagency.controllers.GeographyController;
import com.travelagency.controllers.TripItemController;
import com.travelagency.model.*;
import com.travelagency.rest.AuthenticationResource;
import com.travelagency.rest.TripItemResource;
import com.travelagency.rest.TripResource;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;
import java.util.Optional;

class TestDataCreator {

    private static ConfigurableApplicationContext context;

    TestDataCreator() {
    }

    public void setContext(ConfigurableApplicationContext context) {
        TestDataCreator.context = context;
    }

    public void createTestData() {
        createTestUsers();
        createTestContinents();
        createTestCountries();
        createTestCities();
        createTestTripItems();
        createTestTrips();
    }

    private void createTestUsers() {
        if (context != null) {
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
    }

    private void createTestContinents() {
        if (context != null) {
            String[] names = {"Afrika", "Antarctica", "Azïë", "Europa", "Noord-Amerika", "Oceanië", "Zuid-Amerika"};

            GeographyController geographyController = context.getBean(GeographyController.class);

            for (String name : names) {
                geographyController.createContinent(name);
            }
        }
    }

    private void createTestCountries() {
        createTestCountriesEurope();
        createTestCountriesNorthAmerica();
    }

    private void createTestCities() {
        createTestCitiesEurope();
        createTestCitiesNorthAmerica();
    }

    private void createTestCitiesEurope() {
        if (context != null) {

            String[][] cities = {
                    {"Amsterdam", "Nederland"},
                    {"Rotterdam", "Nederland"},

                    {"Berlijn", "Duitsland"},
                    {"Frankfurt", "Duitsland"},
                    {"Munchen", "Duitsland"},

                    {"Bordeaux", "Frankrijk"},
                    {"Lyon", "Frankrijk"},
                    {"Parijs", "Frankrijk"},
                    {"Perpignan", "Frankrijk"},

                    {"Bergen", "Noorwegen"},
                    {"Oslo", "Noorwegen"},
                    {"Stavanger", "Noorwegen"},
                    {"Tromsø", "Noorwegen"},
                    {"Trondheim", "Noorwegen"},

                    {"Porto", "Portugal"},
                    {"Lissabon", "Portugal"},

                    {"Barcelona", "Spanje"},
                    {"Malaga", "Spanje"},
                    {"Madrid", "Spanje"},
                    {"Valencia", "Spanje"},
            };

            GeographyController geographyController = context.getBean(GeographyController.class);

            for (String[] city : cities) {
                Country country = geographyController.getCountry(city[1]).get();
                geographyController.createCity(city[0], country);
            }
        }
    }

    private void createTestCitiesNorthAmerica()
    {
        if (context != null) {

            String[][] cities = {
                    {"Calgairy", "Canada"},
                    {"Vancouver", "Canada"},
                    {"Toronto", "Canada"},
                    {"Quebec", "Canada"},
                    {"Winnipeg", "Canada"},

                    {"New York", "Verenigde Staten"},
                    {"Boston", "Verenigde Staten"},
                    {"Las Vegas", "Verenigde Staten"},
                    {"Los Angeles", "Verenigde Staten"},
                    {"San Fransisco", "Verenigde Staten"},
                    {"Miami", "Verenigde Staten"},
                    {"Orlando", "Verenigde Staten"},
                    {"Seattle", "Verenigde Staten"},
                    {"Salt Lake City", "Verenigde Staten"},
                    {"Honolulu", "Verenigde Staten"},
                    {"Hilo", "Verenigde Staten"},
                    {"Haleiwa", "Verenigde Staten"},
                    {"Kailua", "Verenigde Staten"},
                    {"Kona", "Verenigde Staten"},
                    {"Lahaina", "Verenigde Staten"},
            };

            GeographyController geographyController = context.getBean(GeographyController.class);

            for (String[] city : cities) {
                Country country = geographyController.getCountry(city[1]).get();
                geographyController.createCity(city[0], country);
            }
        }
    }

    private void createTestCountriesEurope() {
        if (context != null) {
            String[] countries = {
                    "Alandseilanden", "Albanië", "Andorra", "Azerbeidzjan", "Belarus (Wit-Rusland)", "België",
                    "Bosnië en Herzegovina", "Bulgarije", "Cyprus", "Denemarken", "Duitsland", "Engeland (Verenigd Koningrijk)",
                    "Estland", "Farao Eilanden", "Finland", "Frankrijk", "Georgië", "Gibraltar", "Griekenland", "Guernsey",
                    "Hongarije", "Ierland", "IJsland", "Italië", "Jan Mayen", "Jersey", "Kazachstan", "Kosovo", "Kroatië",
                    "Letland", "Liechtenstein", "Litouwen", "Luxemburg", "Macedonië", "Malta", "Man", "Moldavië", "Monaco",
                    "Montenegro", "Nederland", "Noorwegen", "Oekraïne", "Oostenrijk", "Polen", "Portugal", "Roemenië",
                    "Rusland", "Schotland (Verenigd Koninkrijk)", "San Marino", "Servië", "Slovenië", "Slowakije",
                    "Spanje", "Spitsbergen", "Tsjechië", "Turkije", "Vaticaanstad", "Zweden", "Zwitserland"};

            GeographyController geographyController = context.getBean(GeographyController.class);
            Optional<Continent> continent = geographyController.getContinent("Europa");

            if (continent.isPresent()) {
                for (String country : countries) {
                    geographyController.createCountry(country, continent.get());
                }
            }
        }
    }

    private void createTestCountriesNorthAmerica() {
        if (context != null) {
            String[] countries = {"Antigua en Barbuda", "Bahama's", "Barbados", "Belize", "Canada", "Costa Rica",
                    "Cuba", "Dominica", "Dominicaanse Republiek", "El Salvador", "Grenada", "Guatemala", "Haïti",
                    "Honduras", "Jamaica", "Mexico", "Nicaragua", "Panama", "Saint Kitts en Nevis", "Saint Lucia",
                    "Saint Vincent en de Grenadines", "Trinidad en Tobago", "Verenigde Staten"};

            GeographyController geographyController = context.getBean(GeographyController.class);
            Optional<Continent> continent = geographyController.getContinent("Noord-Amerika");

            if (continent.isPresent()) {
                for (String country : countries) {
                    geographyController.createCountry(country, continent.get());
                }
            }
        }
    }

    private void createTestTripItems() {
        if (context != null) {

            Optional<City> city = context.getBean(GeographyController.class).getCity("Honolulu");

            if (city.isPresent()) {
                TripItem tripItem = new TripItem(
                        TripItemType.OUTING,
                        "Turtle Canyon snorkelcruise per catamaran",
                        "Snorkel met groene zeeschildpadden in Oahu's Turtle Canyon tijdens deze 2 tot 3 uur durende tour die vertrekt vanaf Waikiki. Stap aan boord van een gemotoriseerde catamaran en vaar langs de kust van Oahu naar de beste plek op het eiland om de plaatselijke schildpadden te zien. Schildpadwaarnemingen gegarandeerd; als er geen schildpad wordt gezien, krijgt u een gratis tweede cruise. Geniet na het snorkelen van een lunch (indien deze optie is geselecteerd) en de twee inbegrepen drankjes terwijl u blijft uitkijken naar passerende zeedieren zoals dolfijnen en migrerende walvissen. Snorkeluitrusting en vervoer van en naar hotels in Waikiki zijn inbegrepen.",
                        "https://media.tacdn.com/media/attractions-splice-spp-674x446/06/77/93/9b.jpg",
                        new Address("Ergenseen straat 2", city.get(), "90210"),
                        140,
                        new Date());

                context.getBean(TripItemResource.class).createTripItem(tripItem);
            }

            city = context.getBean(GeographyController.class).getCity("Haleiwa");

            if (city.isPresent()) {
                TripItem tripItem = new TripItem(
                        TripItemType.OUTING,
                        "Hang Gliding Hawaii",
                        "Geweldige manier om te zien Oahu vanuit de hemel. Dit is zeker één van de coolste dingen die je gaat doen op deze reis. Vlieg gedurende de tijd van de zonsopkomst door de lucht boven Oahu.",
                        "https://media-cdn.tripadvisor.com/media/photo-o/0a/54/dc/01/lightning-bolt-optical.jpg",
                        new Address("Weet ik veel 4", city.get(), "90212"),
                        140,
                        new Date());

                context.getBean(TripItemResource.class).createTripItem(tripItem);
            }


        }
    }

    private void createTestTrips() {
        if (context != null) {

            Trip trip = new Trip(
                    "14-DAAGSE RONDREIS HAWAIIAN SPLENDORS",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam eget augue iaculis sapien venenatis dapibus. Donec vitae metus eros. Cras imperdiet diam quis metus tempus aliquet. Vivamus ut tortor non elit commodo ultrices. Ut ac aliquam dui. Praesent vel libero lobortis, dapibus elit quis, venenatis mauris. Proin eu tempor leo, ac molestie dolor. Suspendisse potenti. Praesent sed arcu accumsan, congue ligula vitae, varius metus.\n" +
                            "\n" +
                            "Nunc in pharetra odio. Sed finibus venenatis volutpat. Phasellus eget arcu aliquet, placerat ipsum ac, venenatis augue. Aliquam id nibh in lacus mollis posuere. Etiam egestas a mauris nec molestie. Donec lacus diam, placerat a sollicitudin non, volutpat vel leo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed a tristique lectus, vel laoreet massa. Vestibulum euismod commodo purus, ut mollis diam finibus et.\n" +
                            "\n" +
                            "Nullam neque nibh, pharetra quis felis et, maximus sagittis felis. Donec vitae lorem viverra, tincidunt mauris vitae, dictum mauris. In hac habitasse platea dictumst. Cras auctor mi ipsum, ac elementum turpis rutrum eget. Maecenas dignissim aliquet libero, eu pharetra urna. In hac habitasse platea dictumst. Sed facilisis enim at arcu facilisis, vitae cursus enim fermentum. Mauris mattis, arcu a accumsan commodo, sem dolor gravida lectus, non finibus massa eros id metus. Quisque eleifend turpis ac purus convallis dictum. Mauris commodo nisl ut ante malesuada ultrices. Praesent ut dolor sodales, tristique erat ut, porttitor libero. Mauris eu tortor risus. Pellentesque fermentum ligula id venenatis sollicitudin. Cras eu sem ipsum. Aliquam sagittis viverra arcu, sed luctus mi cursus vel.\n" +
                            "\n" +
                            "Nullam convallis, erat sed fermentum blandit, orci mauris pellentesque ipsum, quis luctus sapien magna sit amet libero. Suspendisse placerat auctor nisi in blandit. Donec vestibulum, urna ac pharetra accumsan, diam risus hendrerit felis, eu lacinia turpis ligula ut nisi. Suspendisse semper mauris a arcu scelerisque accumsan. Curabitur efficitur odio nec turpis eleifend lacinia. Nullam ac turpis in nisl ullamcorper commodo malesuada placerat nibh. Integer ultricies aliquam dui pellentesque pulvinar. Duis efficitur, eros ut ullamcorper iaculis, nibh libero fermentum tortor, non fermentum nulla tortor ut ante. Aenean viverra massa ut dui tempor accumsan.\n" +
                            "\n" +
                            "Mauris tristique mollis dolor quis hendrerit. Sed justo lectus, consectetur in arcu et, venenatis iaculis purus. Nunc vel tellus velit. Vestibulum at diam ullamcorper, malesuada massa vel, tempor eros. Etiam fermentum leo felis, ac interdum velit venenatis ac. Nam ultrices turpis quis vehicula semper. Proin hendrerit orci bibendum efficitur blandit. Ut finibus vehicula nunc, a pharetra diam feugiat eu. Nulla in tincidunt dui. Mauris pulvinar est a tortor ultricies, et luctus nisi luctus. Donec suscipit, nunc id dictum dignissim, massa quam sodales ligula, a pellentesque leo nisi quis arcu",
                    "Aloha en welkom op Hawaiiaanse bodem, bekend van rieten rokjes, bloemenkransen en surfers. Natuurlijk is er meer. Per binnenlandse vluchten maak je kennis met de vier grootste eilanden. Relaxen op Waikiki Beach en shoppen in hoofdstad Honolulu op Oahu, actieve vulkanen ontdekken op Big Island, wandelen door tropische landschappen op het groene Kauai en genieten van de zonsondergang op bloemeneiland Maui. Alsof je op ieder eiland in een andere wereld stapt.",
                    "https://media.tuicontent.nl/2E3/2E3BCC2E2A88DA5D714748D39B92E24A.jpg",
                    1599,
                    0);

            Optional<TripItem> tripItem = context.getBean(TripItemController.class).getByCityName("Honolulu");

            if (tripItem.isPresent()) {
                trip.addTripItem(tripItem.get());
            }

            tripItem = context.getBean(TripItemController.class).getByCityName("Haleiwa");

            if (tripItem.isPresent()) {
                trip.addTripItem(tripItem.get());
            }

            context.getBean(TripResource.class).createTrip(trip);

            trip = new Trip(
                    "IN DE BAN VAN HET NOORDERLICHT",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam eget augue iaculis sapien venenatis dapibus. Donec vitae metus eros. Cras imperdiet diam quis metus tempus aliquet. Vivamus ut tortor non elit commodo ultrices. Ut ac aliquam dui. Praesent vel libero lobortis, dapibus elit quis, venenatis mauris. Proin eu tempor leo, ac molestie dolor. Suspendisse potenti. Praesent sed arcu accumsan, congue ligula vitae, varius metus.\n" +
                            "\n" +
                            "Nunc in pharetra odio. Sed finibus venenatis volutpat. Phasellus eget arcu aliquet, placerat ipsum ac, venenatis augue. Aliquam id nibh in lacus mollis posuere. Etiam egestas a mauris nec molestie. Donec lacus diam, placerat a sollicitudin non, volutpat vel leo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed a tristique lectus, vel laoreet massa. Vestibulum euismod commodo purus, ut mollis diam finibus et.\n" +
                            "\n" +
                            "Nullam neque nibh, pharetra quis felis et, maximus sagittis felis. Donec vitae lorem viverra, tincidunt mauris vitae, dictum mauris. In hac habitasse platea dictumst. Cras auctor mi ipsum, ac elementum turpis rutrum eget. Maecenas dignissim aliquet libero, eu pharetra urna. In hac habitasse platea dictumst. Sed facilisis enim at arcu facilisis, vitae cursus enim fermentum. Mauris mattis, arcu a accumsan commodo, sem dolor gravida lectus, non finibus massa eros id metus. Quisque eleifend turpis ac purus convallis dictum. Mauris commodo nisl ut ante malesuada ultrices. Praesent ut dolor sodales, tristique erat ut, porttitor libero. Mauris eu tortor risus. Pellentesque fermentum ligula id venenatis sollicitudin. Cras eu sem ipsum. Aliquam sagittis viverra arcu, sed luctus mi cursus vel.\n" +
                            "\n" +
                            "Nullam convallis, erat sed fermentum blandit, orci mauris pellentesque ipsum, quis luctus sapien magna sit amet libero. Suspendisse placerat auctor nisi in blandit. Donec vestibulum, urna ac pharetra accumsan, diam risus hendrerit felis, eu lacinia turpis ligula ut nisi. Suspendisse semper mauris a arcu scelerisque accumsan. Curabitur efficitur odio nec turpis eleifend lacinia. Nullam ac turpis in nisl ullamcorper commodo malesuada placerat nibh. Integer ultricies aliquam dui pellentesque pulvinar. Duis efficitur, eros ut ullamcorper iaculis, nibh libero fermentum tortor, non fermentum nulla tortor ut ante. Aenean viverra massa ut dui tempor accumsan.\n" +
                            "\n" +
                            "Mauris tristique mollis dolor quis hendrerit. Sed justo lectus, consectetur in arcu et, venenatis iaculis purus. Nunc vel tellus velit. Vestibulum at diam ullamcorper, malesuada massa vel, tempor eros. Etiam fermentum leo felis, ac interdum velit venenatis ac. Nam ultrices turpis quis vehicula semper. Proin hendrerit orci bibendum efficitur blandit. Ut finibus vehicula nunc, a pharetra diam feugiat eu. Nulla in tincidunt dui. Mauris pulvinar est a tortor ultricies, et luctus nisi luctus. Donec suscipit, nunc id dictum dignissim, massa quam sodales ligula, a pellentesque leo nisi quis arcu",
                    "In het najaar, en tot april, valt het leven op IJsland schijnbaar stil. Zowel toeristen als duizenden schapen verlaten de landschappen en maken plaats voor een serene sfeer die het buitenaards karakter van het eiland nog versterkt. Een ideale periode voor wie houdt van een zekere eenzaamheid die de natuur nu uitstraalt. Geen tristesse, maar wel een ‘grootse’ eenzaamheid met een dramatisch kantje afkomstig van de immer aanwezige woeste natuur. Een uniek gevoel!",
                    "https://www.nordic.be/wp-content/uploads/2017/07/IJsland-zuid-geysir-1030x672.jpg",
                    1645,
                    0);

            if (tripItem.isPresent()) {
                trip.addTripItem(tripItem.get());
            }

            context.getBean(TripResource.class).createTrip(trip);
        }
    }
}
