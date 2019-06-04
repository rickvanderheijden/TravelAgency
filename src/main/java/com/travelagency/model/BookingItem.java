package com.travelagency.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bookingitem")
public class BookingItem {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "bookingitem_type", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private BookingItemType bookingItemType;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "description", length = 2000)
    @NotNull
    private String description;

    @Column(name = "price", length = 10)
    @NotNull
    private int price;

    public BookingItem() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookingItemType getBookingItemType() {
        return bookingItemType;
    }

    public void setBookingItemType(BookingItemType bookingItemType) {
        this.bookingItemType = bookingItemType;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
