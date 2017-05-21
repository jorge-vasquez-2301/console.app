package com.example.request;

import org.apache.http.client.HttpClient;

/**
 * The SearchPetByTypeRequest class.
 * @author Jorge Vasquez
 * @since 1.8
 */
public class SearchPetByTypeRequest extends AbstractSearchPetRequest {

    private final String type;

    /**
     * Creates a new instance of CreatePetRequest.
     * @param client  the HTTP client reference
     * @param baseUrl the base URL for the execute
     * @param type    pet's type
     */
    public SearchPetByTypeRequest(HttpClient client, String baseUrl, String type) {
        super(client, baseUrl);
        this.type = type;
    }

    @Override
    public String buildUrl() {
        StringBuilder url = new StringBuilder(baseUrl);
        url.append("type/");
        url.append(type);
        return url.toString();
    }
}
