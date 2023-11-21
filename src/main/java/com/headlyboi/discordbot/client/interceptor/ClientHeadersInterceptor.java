package com.headlyboi.discordbot.client.interceptor;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class ClientHeadersInterceptor implements ClientHttpRequestInterceptor {

    @Value("${tracker.gg.token}")
    private String trackerToken;

    private static final String TRN_API_KEY = "TRN-Api-Key";
    private static final String ACCEPT = "Accept";
    private static final String ACCEPT_ENCODING = "Accept-Encoding";
    private static final String APPLICATION_JSON = "application/json";
    private static final String GZIP = "gzip";

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHeadersInterceptor.class);

    @NotNull
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add(TRN_API_KEY, trackerToken);
        headers.add(ACCEPT, APPLICATION_JSON);
        headers.add(ACCEPT_ENCODING, GZIP);

        logRequestDetails(request);
        return execution.execute(request, body);
    }

    private void logRequestDetails(HttpRequest request) {
        LOGGER.info("Headers: {}", request.getHeaders());
        LOGGER.info("Request Method: {}", request.getMethod());
        LOGGER.info("Request URI: {}", request.getURI());
    }
}
