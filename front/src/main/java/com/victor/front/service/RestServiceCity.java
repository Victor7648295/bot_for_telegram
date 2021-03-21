package com.victor.front.service;

import com.victor.back.model.dto.CityDto;

import java.util.List;
import java.util.Optional;

public interface RestServiceCity {

    Optional<CityDto> createCityDto(CityDto cityDto);

    Optional<CityDto> updateCityDto(CityDto cityDto);

    List<CityDto> getAllCityDto();

    boolean deleteCityById (Long id);
}

