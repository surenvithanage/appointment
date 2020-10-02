package com.web.assignment.appointment.mapping;

import javax.persistence.*;

/**
 * Project appointment
 * User : suren_v
 * Date : 10/2/2020
 * Time : 4:42 PM
 */
@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
