package com.victor.front.controller;

import com.victor.front.config.Constants;
import com.victor.front.service.RestServiceCity;
import com.victor.model.dto.CityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CityController {
    private static final Logger LOG = LoggerFactory.getLogger(CityController.class);

    public static final String BASE_URL = "/city";

    final RestServiceCity service;

    public CityController( RestServiceCity service) {
        this.service = service;
    }

    @GetMapping("/all")
    public String  findAllCity(@ModelAttribute("cityDto") CityDto cityDto,
                               Model model){
        LOG.debug("In CityController - Received GET request to show all city ");
        List<CityDto> listDto =  service.getAllCityDto();
        model.addAttribute("listDto",listDto);
        return Constants.Endpoints.GET_ALL_CITY;
    }

    @PostMapping("/create")
    public String createNewCity(@ModelAttribute("cityDto") CityDto cityDto) {
        LOG.debug("In CityController - Received POST request to show all city ");
        service.createCityDto(cityDto.getName(),cityDto.getDescription());
        return Constants.Endpoints.GET_ALL_CITY;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCity(@PathVariable("id") Long id){
        LOG.debug("In CityController - Received DELETE request to show all city ");
        service.deleteCityById(id);
        return Constants.Endpoints.GET_ALL_CITY;
    }

    @PutMapping("/update")
    public String updateCity(@ModelAttribute("cityDto") CityDto cityDto){
        LOG.debug("In CityController - Received PUT request to update city ");
        service.updateCityDto(cityDto.getName(),cityDto.getDescription());
        return Constants.Endpoints.GET_ALL_CITY;
    }
}



