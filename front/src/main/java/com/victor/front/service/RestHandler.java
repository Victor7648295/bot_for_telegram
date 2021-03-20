package com.victor.front.service;

import org.springframework.http.ResponseEntity;

public interface RestHandler {


    <S> ResponseEntity<S> doGet(String url , Class<S> responseBodyClz);

    <S> ResponseEntity <S> doDelete(String url , Class<S> responseBodyClz);

    <S,T> ResponseEntity<S> doPost(String url , T responseBody, Class<S> responseBodyClz);

    <S,T> ResponseEntity<S> doPut(String url , T responseBody, Class<S> responseBodyClz);
}

