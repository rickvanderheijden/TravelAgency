package com.travelagency.rest.DataTranfersObjects;

public class TripSearchDTO {

    private final String continent;
    private final String country;
    private final String from;
    private final String to;

    public TripSearchDTO(String continent, String country, String from, String to) {
        this.country = country;
        this.continent = continent;
        this.from = from;
        this.to = to;
    }

    public String getCountry() {
        return this.country;
    }

    public String getContinent() {
        return this.continent;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public boolean countryPresent() {
        return this.country != null;
    }

    public boolean continentPresent() {
        return this.continent != null;
    }
}
