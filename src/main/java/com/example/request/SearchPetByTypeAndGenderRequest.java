package com.example.request;

import com.example.Pet;
import org.apache.http.client.HttpClient;

/**
 * The SearchPetByTypeRequest class.
 * @author Jorge Vasquez
 * @since 1.8
 */
public class SearchPetByTypeAndGenderRequest extends AbstractSearchPetRequest {

    private final String type;
    private final Pet.Gender gender;

    /**
     * Creates a new instance of SearchPetByTypeRequest.
     * @param client  the HTTP client reference
     * @param baseUrl the base URL for the execute
     * @param type    pet's type
     * @param gender  pet's gender
     */
    public SearchPetByTypeAndGenderRequest(HttpClient client, String baseUrl, String type, Pet.Gender gender) {
        super(client, baseUrl);
        this.type = type;
        this.gender = gender;
    }

    @Override
    public String buildUrl() {
        StringBuilder url = new StringBuilder(baseUrl);
        url.append("type/");
        url.append(type);
        url.append("?gender=");
        url.append(gender.getValue());
        return url.toString();
    }
}
