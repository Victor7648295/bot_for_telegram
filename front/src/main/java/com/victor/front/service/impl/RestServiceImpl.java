package com.victor.front.service.impl;

import com.victor.front.config.Constants;
import com.victor.front.exception.RestCommunicationException;
import com.victor.front.service.RestHandler;
import com.victor.front.service.RestServiceCity;
import com.victor.model.dto.CityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RestServiceImpl implements RestServiceCity {

    private static final Logger LOG = LoggerFactory.getLogger(RestServiceImpl.class);

    final
    RestHandler rest;

    public RestServiceImpl(RestHandler rest) {
        this.rest = rest;
    }


    @Override
    public CityDto createCityDto(String name, String description) {
        CityDto cityDto = new CityDto(name,description);
        ResponseEntity<CityDto> responseEntity  = rest.doPost(Constants.Endpoints.CREATE_CITY,
                cityDto,
                CityDto.class);
        LOG.debug("In RestCityService - Sending POST request to add new City");
        return responseEntity.getBody();
    }

    @Override
    public CityDto updateCityDto(String name, String description) {
        CityDto cityDto = new CityDto(name,description);
        ResponseEntity<CityDto> responseEntity  = rest.doPut(Constants.Endpoints.UPDATE_CITY,
                cityDto,
                CityDto.class);
        LOG.debug("In RestCityService - Sending POST request to add new City");
        return responseEntity.getBody();
    }

    @Override
    public List<CityDto> getAllCityDto() {
        LOG.debug("In RestCityService - Sending GET request to get all City");
        ResponseEntity<ArrayList> responseEntity = rest.doGet(Constants.Endpoints.GET_ALL_CITY,ArrayList.class);
        if(responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity.getBody();
        }else{
            return Collections.emptyList();
        }
    }

    @Override
    public boolean deleteCityById(Long id) {
        ResponseEntity<Void> responseEntity;
        try{
            responseEntity = rest.doDelete(Constants.Endpoints.DELETE_CITY + id,Void.class);
        }catch (RestCommunicationException e){
            LOG.debug("In RestCityService - Failed delete city with id% " + id,e);
            return false;
        }
        return responseEntity.getStatusCode().is2xxSuccessful();
    }

}

