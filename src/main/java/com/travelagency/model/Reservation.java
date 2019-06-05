package com.travelagency.model;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class Reservation {
    private int numberOfAttendees;
    private Date date;

    public Reservation() {
    }

    public Reservation(int numberOfAttendees, Date date) {
        this.numberOfAttendees = numberOfAttendees;
        this.date = date;
    }

    public void addNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees += numberOfAttendees;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public Date getDate() {
        return date;
    }
}
