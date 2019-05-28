package com.travelagency.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "bookablehotel")
public class BookableHotel extends Hotel {

    @Column(name = "original_hotel_id", length = 10)
    @NotNull
    private long originalHotelId;

    @Column(name = "amount", length = 10)
    @NotNull
    private int amount;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date endDate;

    public BookableHotel() {}

    public BookableHotel(@NotNull long originalHotelId) {
        super();
        this.originalHotelId = originalHotelId;
    }

    public long getOriginalHotelId() {
        return originalHotelId;
    }

    public void setOriginalHotelId(long originalHotelId) {
        this.originalHotelId = originalHotelId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
