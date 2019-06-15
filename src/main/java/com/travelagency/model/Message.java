package com.travelagency.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "message")
    @NotNull
    private String message;

    @Column(name = "sender_id")
    @NotNull
    private Long senderId;

    @Column(name = "travelgroup_id_to")
    private Long travelGroupTo;

    @Column(name = "receiver_id")
    private Long receiverId;

    public Message() {
    }

    public Message(@NotNull String message, @NotNull Long senderId, Long travelGroupTo, Long receiverId) {
        this.message = message;
        this.senderId = senderId;
        this.travelGroupTo = travelGroupTo;
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getTravelGroupTo() {
        return travelGroupTo;
    }

    public void setTravelGroupTo(Long travelGroupTo) {
        this.travelGroupTo = travelGroupTo;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
}
