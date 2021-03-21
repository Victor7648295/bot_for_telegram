package com.victor.back.service.impl;

import com.victor.back.exception.ResourceAlreadyExistInDataBaseException;
import com.victor.back.exception.ResourceNotFoundException;
import com.victor.back.model.City;
import com.victor.back.repository.CityRepository;
import com.victor.back.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

   private static final Logger LOG = LoggerFactory.getLogger(CityServiceImpl.class);

    final
    CityRepository repository;

    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteCity(Long id) {
     City city = repository.findById(id).orElseThrow(
             () -> new ResourceNotFoundException("In City Service - Not found city with id :" + id));
     repository.delete(city);
     LOG.debug("In City Service - Successfully delete city with id " + id);

    }

    @Override
    public void addCity(City city) {
        Optional<City> oldCity = repository.findByName(city.getName());
        if(oldCity.isPresent()) {
            throw new ResourceAlreadyExistInDataBaseException(
                    "In City Service - failed to create city ,a city with that name already exists " + city.getName());
        }
        repository.save(city);
        LOG.debug("In City Service - Successfully create city with id " + city);
    }

    @Override
    public void updateCity(City city) {
        Optional<City> oldCity = findById(city.getId());
        if(oldCity.isPresent()) {
            repository.saveAndFlush(city);
            LOG.debug("In City Service - Successfully update city " + city);
        }else{
            throw new ResourceNotFoundException("In City Service - Not Found city with id : "+ city.getId());
        }
    }

    @Override
    public List<City> getAllCity() {
        return repository.findAll();
    }

    @Override
    public Optional<City> findById(Long id) {
        return repository.findById(id);
    }
}
