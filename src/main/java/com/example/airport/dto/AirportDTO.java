package com.example.airport.dto;

public class AirportDTO {
    private Long id;
    private String name;
    private String code;
    private String country;
    private String city;

    public AirportDTO(Long id, String name, String code, String country, String city) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.country = country;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
