package com.travelagency.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;


    /** Model created at timestamp. */
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;


    @Column(name = "name", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "amount", length = 50)
    @NotNull
    private double amount;

    public Payment() {
    }

    public Payment(@NotNull PaymentMethod method, Booking booking, User user, @NotNull double amount) {
        this.method = method;
        this.booking = booking;
        this.user = user;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    /** Created at getter. */
    public Date getCreatedAt() {
        return (Date) createdAt.clone();
    }

    /** Created at setter. */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = (Date) createdAt.clone();
    }

}
