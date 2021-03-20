package com.victor.front.service;

import com.victor.model.dto.CityDto;

import java.util.List;

public interface RestServiceCity {

    CityDto createCityDto(String name , String description);

    CityDto updateCityDto(String name , String description);

    List<CityDto> getAllCityDto();

    boolean deleteCityById (Long id);
}

