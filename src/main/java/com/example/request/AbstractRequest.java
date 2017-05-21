package com.example.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;

import java.io.IOException;

/**
 * The AbstractRequest class.
 * Used as a template for creating requests.
 * @author Jorge Vasquez
 * @since 1.8
 */
public abstract class AbstractRequest<T> {

    final HttpClient client;
    final String baseUrl;

    /**
     * Constructor for AbstractRequest.
     * @param client  the HTTP client reference
     * @param baseUrl the base URL for requests
     */
    AbstractRequest(HttpClient client, String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
    }

    /**
     * Executes this.
     * @return the response for this request
     * @throws IOException
     */
    public final T execute() throws IOException {
        return parseResponse(getResponse(buildUrl()));
    }

    /**
     * Builds the URL for executing the request.
     * @return the URL
     */
    protected abstract String buildUrl();

    /**
     * Returns the response for this request.
     * @param url the URL for the request
     * @return the HTTP response for this request
     * @throws IOException
     */
    protected abstract HttpResponse getResponse(String url) throws IOException;

    /**
     * Parses the HTTP response for this request
     * @param response the HTTP response for this request
     * @return the parsed HTTP response
     */
    protected abstract T parseResponse(HttpResponse response);
}
