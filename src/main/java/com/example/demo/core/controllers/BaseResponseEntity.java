package com.example.demo.core.controllers;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class BaseResponseEntity<T> extends ResponseEntity<T> {

    @Nullable
    private T body;

    public BaseResponseEntity(HttpStatusCode status) {
        super(status);
    }

    public BaseResponseEntity(T body, HttpStatusCode status) {
        super(body, status);
    }

    public BaseResponseEntity(MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(headers, status);
    }

    public BaseResponseEntity(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }

    public BaseResponseEntity(T body, MultiValueMap<String, String> headers, HttpStatusCode statusCode) {
        super(body, headers, statusCode);
    }

    public BaseResponseEntity(T body, MultiValueMap<String, String> headers, HttpStatusCode statusCode, String message) {

        super(body, headers, statusCode);
    }
}
