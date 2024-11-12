package com.example.airport.service;

import com.example.airport.dto.FlightRequest;
import com.example.airport.model.Aircraft;
import com.example.airport.model.Airport;
import com.example.airport.model.Flight;
import com.example.airport.repository.AircraftRepository;
import com.example.airport.repository.AirportRepository;
import com.example.airport.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final AircraftRepository aircraftRepository;
    private final AirportRepository airportRepository;

    public FlightService(FlightRepository flightRepository, AircraftRepository aircraftRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.aircraftRepository = aircraftRepository;
        this.airportRepository = airportRepository;
    }

    public Flight createFlight(FlightRequest request) {
        Aircraft aircraft = aircraftRepository.findById(request.getAircraftId())
                .orElseThrow(() -> new IllegalArgumentException("Aircraft not found"));

        Airport fromAirport = airportRepository.findById(request.getFromAirportId())
                .orElseThrow(() -> new IllegalArgumentException("From Airport not found"));

        Airport toAirport = airportRepository.findById(request.getToAirportId())
                .orElseThrow(() -> new IllegalArgumentException("To Airport not found"));

        Flight flight = new Flight();
        flight.setAircraft(aircraft);
        flight.setFromAirport(fromAirport);
        flight.setToAirport(toAirport);
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());

        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> searchFlights(String fromCity, String toCity) {
        return flightRepository.findByFromAirportCityAndToAirportCity(fromCity, toCity);
    }

    public List<Flight> searchFlightsByDate(String fromCity, String toCity, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return flightRepository.findByFromAirportCityAndToAirportCityAndDepartureTimeBetween(
                fromCity, toCity, startOfDay, endOfDay
        );
    }
}
