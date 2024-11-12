package com.example.airport.dto;

public class TicketDTO {
    private Long id;
    private String passengerName;
    private String seatNumber;
    private String seatClass;
    private String userEmail;

    public TicketDTO(Long id, String passengerName, String seatNumber, String seatClass, String userEmail) {
        this.id = id;
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
        this.seatClass = seatClass;
        this.userEmail = userEmail;
    }

    public Long getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
