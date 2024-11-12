package com.example.airport.controller;

import com.example.airport.dto.TicketDTO;
import com.example.airport.dto.TicketRequest;
import com.example.airport.model.Ticket;
import com.example.airport.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets(@RequestParam(required = false) Long userId) {
        List<Ticket> tickets = ticketService.getAllTickets(userId);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest request) {
        Ticket ticket = ticketService.createTicket(request);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<TicketDTO>> getTicketsByFlight(@PathVariable Long flightId) {
        List<TicketDTO> tickets = ticketService.getTicketsByFlight(flightId);
        return ResponseEntity.ok(tickets);
    }
}
