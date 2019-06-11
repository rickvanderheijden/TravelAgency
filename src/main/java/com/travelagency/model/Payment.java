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

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "amount", length = 50)
    @NotNull
    private double amount;

    public Payment() {
    }

    public Payment(@NotNull PaymentMethod method, Long bookingId, Long userId, @NotNull double amount) {
        this.method = method;
        this.bookingId = bookingId;
        this.userId = userId;
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

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
