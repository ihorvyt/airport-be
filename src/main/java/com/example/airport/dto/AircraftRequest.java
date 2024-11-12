package com.example.airport.dto;

import java.util.List;

public class AircraftRequest {
    private String name;
    private List<SeatRequest> seats;

    // Геттери та сеттери
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SeatRequest> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatRequest> seats) {
        this.seats = seats;
    }
}