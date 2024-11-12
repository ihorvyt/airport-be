package com.example.airport.controller;

import com.example.airport.dto.AirportDTO;
import com.example.airport.model.Airport;
import com.example.airport.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public ResponseEntity<AirportDTO> createAirport(@RequestBody Airport airport) {
        AirportDTO createdAirport = airportService.createAirport(airport);
        return ResponseEntity.ok(createdAirport);
    }

    @GetMapping
    public ResponseEntity<List<AirportDTO>> getAllAirports() {
        List<AirportDTO> airports = airportService.getAllAirports();
        return ResponseEntity.ok(airports);
    }

}
