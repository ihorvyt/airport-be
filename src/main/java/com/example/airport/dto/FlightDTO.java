package com.example.airport.dto;

public class FlightDTO {
    private Long id;
    private String aircraftName;
    private AirportDTO fromAirport;
    private AirportDTO toAirport;
    private String departureTime;
    private String arrivalTime;

    public FlightDTO(Long id, String aircraftName, AirportDTO fromAirport, AirportDTO toAirport, String departureTime, String arrivalTime) {
        this.id = id;
        this.aircraftName = aircraftName;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
}