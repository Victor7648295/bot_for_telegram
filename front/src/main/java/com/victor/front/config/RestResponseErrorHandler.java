package com.victor.front.config;

import com.victor.front.exception.RestCommunicationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

import static java.lang.String.format;

public class RestResponseErrorHandler implements ResponseErrorHandler {

    private static final String REQUEST_FAILED_WITH_DETAILS = "%s request to URL:[%s] failed with status code:[%s]:%s";

    private static final String REQUEST_FAILED = "Request failed with status code:[%s]:%s";

    private final List<HttpStatus> acceptableStatuses;

    public RestResponseErrorHandler(List<HttpStatus> acceptableStatuses) {
        this.acceptableStatuses = acceptableStatuses;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !acceptableStatuses.contains(response.getStatusCode());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new RestCommunicationException(format(
                REQUEST_FAILED,
                response.getRawStatusCode(),
                bodyAsString(response.getBody())));
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        throw new RestCommunicationException(format(
                REQUEST_FAILED_WITH_DETAILS,
                method,
                url,
                response.getRawStatusCode(),
                bodyAsString(response.getBody())));
    }

    private String bodyAsString(InputStream responseBody) throws IOException {
        return StreamUtils.copyToString(responseBody, Charset.defaultCharset());
    }
}

