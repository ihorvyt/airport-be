package com.example.airport.controller;

import com.example.airport.dto.AircraftRequest;
import com.example.airport.model.Aircraft;
import com.example.airport.service.AircraftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aircrafts")
public class AircraftController {
    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @PostMapping
    public ResponseEntity<?> createAircraft(@RequestBody AircraftRequest request) {
        try {
            Aircraft aircraft = aircraftService.createAircraft(request);
            return ResponseEntity.ok(aircraft);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
