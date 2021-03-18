package com.victor.service;

import com.victor.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {

    void deleteCity(Long id);

    void addCity(City city);

    void updateCity (City city);

    List<City> getAllCity();

    Optional<City> findById(Long id);
}
