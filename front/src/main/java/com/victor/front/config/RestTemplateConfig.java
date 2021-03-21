package com.victor.front.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpStatus;

import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(
            @Value("${rest.accepted-status-codes}") List<Integer> acceptedStatusCodes,
            RestTemplateBuilder builder) {
        List<HttpStatus> acceptedStatuses = acceptedStatusCodes.stream().map(HttpStatus::resolve).collect(toList());
        return builder.errorHandler(new RestResponseErrorHandler(acceptedStatuses)).build();
    }

}
