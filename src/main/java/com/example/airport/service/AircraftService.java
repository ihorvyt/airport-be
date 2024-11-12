package com.example.airport.service;

import com.example.airport.dto.AircraftRequest;
import com.example.airport.model.Aircraft;
import com.example.airport.model.Seat;
import com.example.airport.repository.AircraftRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public Aircraft createAircraft(AircraftRequest request) {
        Optional<Aircraft> existingAircraft = aircraftRepository.findByName(request.getName());
        if (existingAircraft.isPresent()) {
            throw new IllegalArgumentException("Aircraft with name '" + request.getName() + "' already exists.");
        }

        Aircraft aircraft = new Aircraft();
        aircraft.setName(request.getName());

        List<Seat> seats = new ArrayList<>();
        for (var seatRequest : request.getSeats()) {
            Seat seat = new Seat();
            seat.setSeatNumber(seatRequest.getSeatNumber());
            seat.setNearWindow(seatRequest.isNearWindow());
            seat.setSeatClass(seatRequest.getSeatClass());
            seat.setAircraft(aircraft);
            seats.add(seat);
        }
        aircraft.setSeats(seats);

        return aircraftRepository.save(aircraft);
    }
}
