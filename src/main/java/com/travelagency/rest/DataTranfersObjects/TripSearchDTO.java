package com.travelagency.rest.DataTranfersObjects;

import java.util.Date;

public class TripSearchDTO {

    private final String continent;
    private final String country;
    private final Date from;
    private final Date to;
    private final String keyword;

    public TripSearchDTO(String continent, String country, Date from, Date to, String keyword) {
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

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
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

    public boolean fromPresent() { return this.from != null; }

    public boolean toPresent() { return this.to != null; }
}
