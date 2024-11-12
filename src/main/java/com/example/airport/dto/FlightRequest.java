package com.example.airport.dto;

import java.time.LocalDateTime;

public class FlightRequest {
    private Long aircraftId;         // ID літака
    private Long fromAirportId;      // ID аеропорту відправлення
    private Long toAirportId;        // ID аеропорту прибуття
    private LocalDateTime departureTime; // Час відправлення
    private LocalDateTime arrivalTime;   // Час прибуття

    // Геттери
    public Long getAircraftId() {
        return aircraftId;
    }

    public Long getFromAirportId() {
        return fromAirportId;
    }

    public Long getToAirportId() {
        return toAirportId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public void setFromAirportId(Long fromAirportId) {
        this.fromAirportId = fromAirportId;
    }

    public void setToAirportId(Long toAirportId) {
        this.toAirportId = toAirportId;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
