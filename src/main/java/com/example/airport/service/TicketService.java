package com.example.airport.service;

import com.example.airport.dto.TicketDTO;
import com.example.airport.dto.TicketRequest;
import com.example.airport.model.Flight;
import com.example.airport.model.Seat;
import com.example.airport.model.Ticket;
import com.example.airport.model.User;
import com.example.airport.repository.FlightRepository;
import com.example.airport.repository.SeatRepository;
import com.example.airport.repository.TicketRepository;
import com.example.airport.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, FlightRepository flightRepository, SeatRepository seatRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
    }

    public List<Ticket> getAllTickets(Long userId) {
        if (userId != null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            return ticketRepository.findAllByUser(user);
        }
        return ticketRepository.findAll();
    }

    public Ticket createTicket(TicketRequest request) {
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        Seat seat = seatRepository.findById(request.getSeatId())
                .orElseThrow(() -> new IllegalArgumentException("Seat not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (ticketRepository.findByFlightAndSeat(flight, seat).isPresent()) {
            throw new IllegalArgumentException("Seat already booked");
        }

        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setSeat(seat);
        ticket.setUser(user);
        ticket.setPassengerName(request.getPassengerName());

        return ticketRepository.save(ticket);
    }

    public List<TicketDTO> getTicketsByFlight(Long flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));
        return ticketRepository.findAllByFlight(flight).stream()
                .map(ticket -> new TicketDTO(
                        ticket.getId(),
                        ticket.getPassengerName(),
                        ticket.getSeat().getSeatNumber(),
                        ticket.getSeat().getSeatClass().name(),
                        ticket.getUser().getEmail()
                ))
                .collect(Collectors.toList());
    }

}
