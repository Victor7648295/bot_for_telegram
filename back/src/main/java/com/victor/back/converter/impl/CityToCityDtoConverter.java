package com.victor.back.converter.impl;

import com.victor.back.converter.TypeConverter;
import com.victor.back.model.City;
import com.victor.back.model.dto.CityDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityToCityDtoConverter implements TypeConverter<City, CityDto> {
    @Override
    public Class<City> getSourceClass() {
        return City.class;
    }

    @Override
    public Class<CityDto> getTargetClass() {
        return CityDto.class;
    }

    @Override
    public CityDto convert(City source) {
        return new CityDto(source.getId(),source.getName(),source.getDescription());
    }

    @Override
    public List<CityDto> convertList(Collection<City> sourceList) {
        return sourceList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
