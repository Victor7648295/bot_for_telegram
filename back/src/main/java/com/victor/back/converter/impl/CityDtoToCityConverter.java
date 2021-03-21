package com.victor.back.converter.impl;

import com.victor.back.converter.TypeConverter;
import com.victor.back.model.City;
import com.victor.back.model.dto.CityDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityDtoToCityConverter implements TypeConverter<CityDto,City> {

    @Override
    public Class<CityDto> getSourceClass() {
        return CityDto.class;
    }

    @Override
    public Class<City> getTargetClass() {
        return City.class;
    }

    @Override
    public City convert(CityDto source) {
        return new City(source.getId(),source.getName(),source.getDescription());
    }

    @Override
    public List<City> convertList(Collection<CityDto> sourceList) {
        return sourceList
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
