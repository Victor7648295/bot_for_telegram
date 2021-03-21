package com.victor.front.controller;

import com.victor.front.service.RestServiceCity;
import com.victor.back.model.dto.CityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(CityController.BASE_URL)
public class CityController {
    private static final Logger LOG = LoggerFactory.getLogger(CityController.class);

    public static final String BASE_URL = "/city" ;

    public static final String RETURN_URL = "redirect:/city";

    final RestServiceCity service;

    public CityController( RestServiceCity service) {
        this.service = service;
    }

    @GetMapping
    public String  findAllCity(@ModelAttribute("cityDto") CityDto cityDto,
                               Model model){
        LOG.debug("In CityController - Received GET request to show all city ");
        List<CityDto> listDto =  service.getAllCityDto();
        model.addAttribute("listDto",listDto);
        return "cityTemplate";
    }

    @PostMapping("/create")
    public String createNewCity(@ModelAttribute("cityDto") CityDto cityDto) {
        LOG.debug("In CityController - Received POST request to show all city ");
        service.createCityDto(cityDto);
        return RETURN_URL;
    }

    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable("id") Long id){
        LOG.debug("In CityController - Received DELETE request to show all city ");
        service.deleteCityById(id);
        return RETURN_URL;
    }

    @PostMapping("/update")
    public String updateCity(@ModelAttribute("cityDto") CityDto cityDto){
        LOG.debug("In CityController - Received PUT request to update city ");
        service.updateCityDto(cityDto);
        return RETURN_URL;
    }
}



