package com.travelagency.model;

import javax.persistence.*;

@Entity
@Table(name = "destination")
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "user_id_from")
    private Long userFrom;

    @Column(name = "travelgroup_id_to")
    private Long travelGroupTo;

    @Column(name = "user_id_to")
    private Long userTo;

    public Message() {
    }

    public Message(String message, Long userFrom, Long travelGroupTo, Long userTo) {
        this.message = message;
        this.userFrom = userFrom;
        this.travelGroupTo = travelGroupTo;
        this.userTo = userTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Long userFrom) {
        this.userFrom = userFrom;
    }

    public Long getTravelGroupTo() {
        return travelGroupTo;
    }

    public void setTravelGroupTo(Long travelGroupTo) {
        this.travelGroupTo = travelGroupTo;
    }

    public Long getUserTo() {
        return userTo;
    }

    public void setUserTo(Long userTo) {
        this.userTo = userTo;
    }
}
