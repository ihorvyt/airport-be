package com.example.airport.repository;

import com.example.airport.model.Flight;
import com.example.airport.model.Seat;
import com.example.airport.model.Ticket;
import com.example.airport.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByFlightAndSeat(Flight flight, Seat seat);
    List<Ticket> findAllByFlight(Flight flight);
    List<Ticket> findAllByUser(User user); // Fetch tickets by user
}
