package com.victor.api.v1.resource;

import com.victor.config.ApiVersion;
import com.victor.converter.impl.CityDtoToCityConverter;
import com.victor.converter.impl.CityToCityDtoConverter;
import com.victor.exception.ResourceNotFoundException;
import com.victor.model.City;
import com.victor.model.dto.CityDto;
import com.victor.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

import static com.victor.util.WebUtil.getFullRequestUri;

@RestController
@RequestMapping(path = CityResource.BASE_URL)
public class CityResource {

    private static final Logger LOG = LoggerFactory.getLogger(CityResource.class);

    public static final String BASE_URL = ApiVersion.VERSION_1_0 + "/city";

    final
    CityDtoToCityConverter converter;

    final
    CityToCityDtoConverter converterToDto;

    final
    CityService service;

    public CityResource(CityService service, CityToCityDtoConverter converterToDto, CityDtoToCityConverter converter) {

        this.service = service;
        this.converterToDto = converterToDto;
        this.converter = converter;
    }

    @PostMapping("/create")
    public void createCity(@RequestBody @Valid CityDto cityDto){
        City city = converter.convert(cityDto);
        service.addCity(city);
        LOG.debug("In City Resource - Received POST request to create new City " + getFullRequestUri());
    }

    @PutMapping("/update")
    public void updateCity(@RequestBody @Valid CityDto cityDto){
        City city = converter.convert(cityDto);
        service.updateCity(city);
        LOG.debug("In City Resource - Received PUT request to update City " + getFullRequestUri());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCity(@PathVariable ("id") Long id){
        if(id != null){
            service.deleteCity(id);
            LOG.debug("In City Resource - Received Delete request to delete City with id " + id);
        }else{
            throw new ResourceNotFoundException("In City Resource - received request to delete City with id = null");
        }
    }

    @GetMapping("/all")
    public List<CityDto> getAllCity(){
        List<City> list = service.getAllCity();
        LOG.debug("In City Resource - received request to get all city" + getFullRequestUri());
        return converterToDto.convertList(list);
    }

}
