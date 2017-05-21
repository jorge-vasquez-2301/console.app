package com.example.request;

import org.apache.http.client.HttpClient;

/**
 * The SearchPetByNameRequest class.
 * @author Jorge Vasquez
 * @since 1.8
 */
public class SearchPetByNameRequest extends AbstractSearchPetRequest {

    private final String name;

    /**
     * Creates a new instance of CreatePetRequest.
     * @param client  the HTTP client reference
     * @param baseUrl the base URL for the execute
     * @param name    pet's name
     */
    public SearchPetByNameRequest(HttpClient client, String baseUrl, String name) {
        super(client, baseUrl);
        this.name = name;
    }

    @Override
    public String buildUrl() {
        StringBuilder url = new StringBuilder(baseUrl);
        url.append("name/");
        url.append(name);
        return url.toString();
    }
}
