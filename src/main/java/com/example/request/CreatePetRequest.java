package com.example.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import java.io.IOException;

/**
 * The CreatePetRequest class.
 * @author Jorge Vasquez
 * @since 1.8
 */
public class CreatePetRequest extends AbstractRequest<Integer> {

    private final String petType;
    private final String name;
    private final String gender;
    private final String timestamp;

    /**
     * Creates a new instance of CreatePetRequest.
     * @param client    the HTTP client reference
     * @param baseUrl   the base URL for the execute
     * @param petType   pet's type
     * @param name      pet's name
     * @param gender    pet's gender
     * @param timestamp pet's creation timestamp
     */
    public CreatePetRequest(HttpClient client, String baseUrl, String petType, String name, String gender, String timestamp) {
        super(client, baseUrl);
        this.petType = petType;
        this.name = name;
        this.gender = gender;
        this.timestamp = timestamp;
    }

    @Override
    public String buildUrl() {
        StringBuilder url = new StringBuilder(baseUrl);
        url.append("new/");
        url.append(petType);
        url.append('/');
        url.append(name);
        url.append('/');
        url.append(gender);
        url.append('/');
        url.append(timestamp);
        return url.toString();
    }

    @Override
    public HttpResponse getResponse(String url) throws IOException {
        HttpPost post = new HttpPost(url);
        return client.execute(post);
    }

    @Override
    public Integer parseResponse(HttpResponse response) {
        try {
            response.getEntity().consumeContent();
            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            return null;
        }
    }
}
