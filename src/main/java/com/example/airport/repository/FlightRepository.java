package com.example.airport.repository;

import com.example.airport.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFromAirportCityAndToAirportCity(String fromCity, String toCity);

    List<Flight> findByFromAirportCityAndToAirportCityAndDepartureTimeBetween(
            String fromCity, String toCity, LocalDateTime startOfDay, LocalDateTime endOfDay
    );
}
