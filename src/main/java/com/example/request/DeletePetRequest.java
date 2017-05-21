package com.example.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;

import java.io.IOException;

/**
 * The DeletePetRequest class.
 * @author Jorge Vasquez
 * @since 1.8
 */
public class DeletePetRequest extends AbstractRequest<Integer> {

    private final long id;

    /**
     * Creates a new instance of DeletePetRequest.
     * @param client  the HTTP client reference
     * @param baseUrl the base URL for the execute
     * @param id      pet's id
     */
    public DeletePetRequest(HttpClient client, String baseUrl, long id) {
        super(client, baseUrl);
        this.id = id;
    }

    @Override
    public String buildUrl() {
        StringBuilder url = new StringBuilder(baseUrl);
        url.append("delete/");
        url.append(id);
        return url.toString();
    }

    @Override
    public HttpResponse getResponse(String url) throws IOException {
        HttpDelete delete = new HttpDelete(url);
        return client.execute(delete);
    }

    @Override
    public Integer parseResponse(HttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }
}
