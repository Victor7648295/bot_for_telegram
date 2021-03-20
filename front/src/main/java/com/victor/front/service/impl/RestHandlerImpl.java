package com.victor.front.service.impl;

import com.victor.front.service.RestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.*;

import org.springframework.web.client.RestOperations;



public class RestHandlerImpl implements RestHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestHandlerImpl.class);

    private final String backendBaseUrl = "http://localhost:8888/city";

    private final String backendApiVersion = "/v1.0";

    final RestOperations rest;

    public RestHandlerImpl(RestOperations rest) {
        this.rest = rest;

    }

    @Override
    public <S> ResponseEntity<S> doGet(String url, Class<S> responseBodyClz) {
        String requestUrl = backendBaseUrl + backendApiVersion + url ;
        HttpHeaders requestHeader = createRequestHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeader);

        ResponseEntity<S> response = rest.exchange(requestUrl, HttpMethod.GET,requestEntity,responseBodyClz);
        LOG.debug("In doGet - GET request to URL:[{}] was successful", requestUrl);
        return response;
    }

    @Override
    public <S> ResponseEntity<S> doDelete(String url, Class<S> responseBodyClz) {
        String requestUrl = backendBaseUrl + backendApiVersion + url ;
        HttpHeaders requestHeader = createRequestHeaders();
        HttpEntity<Void> requestEntity = new HttpEntity<>(requestHeader);

        ResponseEntity<S> response = rest.exchange(requestUrl, HttpMethod.DELETE,requestEntity,responseBodyClz);
        LOG.debug("In doGet - DELETE request to URL:[{}] was successful", requestUrl);
        return response;
    }

    @Override
    public <S, T> ResponseEntity<S> doPost(String url, T responseBody, Class<S> responseBodyClz) {
        String requestUrl = backendBaseUrl + backendApiVersion + url ;
        HttpHeaders requestHeader = createRequestHeaders();
        HttpEntity<T> requestEntity = new HttpEntity<>(responseBody,requestHeader);

        ResponseEntity<S> response = rest.postForEntity(requestUrl,requestEntity,responseBodyClz);
        LOG.debug("In doGet - POST request to URL:[{}] was successful", requestUrl);
        return response;
    }

    @Override
    public <S, T> ResponseEntity<S> doPut(String url, T responseBody, Class<S> responseBodyClz) {
        String requestUrl = backendBaseUrl + backendApiVersion + url ;
        HttpHeaders requestHeader = createRequestHeaders();
        HttpEntity<T> requestEntity = new HttpEntity<>(responseBody,requestHeader);

        ResponseEntity<S> response = rest.postForEntity(requestUrl,requestEntity,responseBodyClz);
        LOG.debug("In doGet - PUT request to URL:[{}] was successful", requestUrl);
        return response;
    }

    private HttpHeaders createRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}


