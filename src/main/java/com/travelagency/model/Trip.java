package com.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Range;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "summary", length = 500)
    @NotNull
    private String summary;

    @Column(name = "description", length = 5000)
    @NotNull
    private String description;

    @Column(name = "image_blob")
    @NotNull
    @Lob
    private String imageBlob;

    @Column(name = "total_price", length = 10)
    @NotNull
    private int totalPrice;

    @Column(name = "discount", length = 10)
    private int discount;

    @Column(name = "max_persons", length = 10)
    @Range(min = 1, max = 1000)
    @NotNull
    private int maximumNumberOfTravelers;

    @Column(name = "min_persons", length = 10)
    @Range(min = 1, max = 1000)
    @NotNull
    private int minimumNumberOfTravelers;

    @Column(name = "available_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date availableFrom;

    @Column(name = "available_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date availableTo;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(name = "trip_destination",
            joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "destination_id", referencedColumnName = "id"))
    private List<Destination> destinations;

    public Trip() {}

    public Trip(
            @NotNull String name,
            @NotNull String description,
            @NotNull String summary,
            String imageBlob,
            @NotNull int totalPrice,
            int discount) {
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.imageBlob = imageBlob;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.minimumNumberOfTravelers = 1;
        this.maximumNumberOfTravelers = 20;
        this.availableFrom = parseDate("2019-01-01");
        this.availableTo = parseDate("2019-12-31");
        this.destinations = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(String imageBlob) {
        this.imageBlob = imageBlob;
    }

    public int getMaximumNumberOfTravelers() {
        return maximumNumberOfTravelers;
    }

    public void setMaximumNumberOfTravelers(int maximumNumberOfTravelers) {
        this.maximumNumberOfTravelers = maximumNumberOfTravelers;
    }

    public int getMinimumNumberOfTravelers() {
        return minimumNumberOfTravelers;
    }

    public void setMinimumNumberOfTravelers(int minimumNumberOfTravelers) {
        this.minimumNumberOfTravelers = minimumNumberOfTravelers;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        if (destinations == null) {
            this.destinations = new ArrayList<>();
        } else {
            this.destinations = destinations;
        }
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean addDestination(Destination destination){

        if(destinations.contains(destination))
            return false;

        return destinations.add(destination);
    }

    public boolean removeDestination(Destination destination){
        if(!destinations.contains(destination))
            return false;

        return destinations.remove(destination);
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
