package com.example.request;

import com.example.Pet;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The AbstractSearchPetRequest class.
 * Used as a template for creating pet search requests.
 * @author Jorge Vasquez
 * @since 1.8
 */
public abstract class AbstractSearchPetRequest extends AbstractRequest<List<Pet>> {

    /**
     * Constructor for AbstractSearchPetRequest.
     * @param client  the HTTP client reference
     * @param baseUrl the base URL for the execute
     */
    AbstractSearchPetRequest(HttpClient client, String baseUrl) {
        super(client, baseUrl);
    }

    @Override
    public HttpResponse getResponse(String url) throws IOException {
        HttpGet get = new HttpGet(url);
        return client.execute(get);
    }

    @Override
    public List<Pet> parseResponse(HttpResponse response) {
        return parseJson(response);
    }

    /**
     * Parses JSON data received in an HTTP response.
     * @param response the HTTP response to parse
     * @return a list of pets
     */
    private List<Pet> parseJson(HttpResponse response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Pet>> mapType = new TypeReference<List<Pet>>() {
            };
            JsonFactory jsonFactory = new JsonFactory();
            byte[] json = IOUtils.toByteArray(response.getEntity().getContent());
            JsonParser parser = jsonFactory.createParser(json);
            return mapper.readValue(parser, mapType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
