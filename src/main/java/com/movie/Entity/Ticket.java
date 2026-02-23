package com.movie.Entity;


import jakarta.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movie;
    private Integer members;

    public Ticket() {}

    public Ticket(String movie, int members) {
        this.movie = movie;
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Integer getMembers() {
        return members;
    }

    public void setMembers(Integer members) {
        this.members = members;
    }
}
