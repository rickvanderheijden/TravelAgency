package com.travelagency.rest.DataTranfersObjects;

public class TripSearchDTO {

    private final String continent;
    private final String country;
    private final String from;
    private final String to;
    private final String keyword;

    public TripSearchDTO(String continent, String country, String from, String to, String keyword) {
        this.country = country;
        this.continent = continent;
        this.from = from;
        this.to = to;
        this.keyword = keyword;
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

    public String getKeyword() { return this.keyword; }

    public boolean countryPresent() {
        return this.country != null;
    }

    public boolean continentPresent() {
        return this.continent != null;
    }

    public boolean keywordPresent() { return this.keyword != null; }
}
