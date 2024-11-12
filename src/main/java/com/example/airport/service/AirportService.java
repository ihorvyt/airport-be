package com.example.airport.service;

import com.example.airport.dto.AirportDTO;
import com.example.airport.model.Airport;
import com.example.airport.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public AirportDTO createAirport(Airport airport) {
        Airport savedAirport = airportRepository.save(airport);
        return new AirportDTO(
                savedAirport.getId(),
                savedAirport.getName(),
                savedAirport.getCode(),
                savedAirport.getCountry(),
                savedAirport.getCity()
        );
    }

    public List<AirportDTO> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(airport -> new AirportDTO(
                        airport.getId(),
                        airport.getName(),
                        airport.getCode(),
                        airport.getCountry(),
                        airport.getCity()
                ))
                .collect(Collectors.toList());
    }
}
