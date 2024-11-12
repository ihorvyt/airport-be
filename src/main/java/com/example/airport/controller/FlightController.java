package com.example.airport.controller;

import com.example.airport.dto.FlightRequest;
import com.example.airport.model.Flight;
import com.example.airport.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody FlightRequest request) {
        return ResponseEntity.ok(flightService.createFlight(request));
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String fromCity,
            @RequestParam String toCity
    ) {
        List<Flight> flights = flightService.searchFlights(fromCity, toCity);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/search-by-date")
    public ResponseEntity<List<Flight>> searchFlightsByDate(
            @RequestParam String fromCity,
            @RequestParam String toCity,
            @RequestParam String date // Очікуємо формат yyyy-MM-dd
    ) {
        LocalDate parsedDate = LocalDate.parse(date);
        List<Flight> flights = flightService.searchFlightsByDate(fromCity, toCity, parsedDate);
        return ResponseEntity.ok(flights);
    }
}