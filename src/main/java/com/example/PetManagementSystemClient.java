package com.example;

import com.example.request.*;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.List;

/**
 * The PetManagementSystemClient class.
 * This is the REST client class for the Pet Management System.
 * @author Jorge Vasquez
 * @since 1.8
 */
public class PetManagementSystemClient {

    private final HttpClient client;
    private final String baseUrl;

    /**
     * Creates a new instance of PetManagementSystemClient.
     * @param address REST server address
     * @param port    REST server port
     */
    public PetManagementSystemClient(String address, short port) {
        client = new DefaultHttpClient();
        StringBuilder url = new StringBuilder("http://");
        url.append(address);
        url.append(':');
        url.append(port);
        url.append("/pets/");
        baseUrl = url.toString();
    }

    /**
     * This action creates a new pet for the given parameters.
     * @param type      The pet's type (i.e. dog, cat, ...)
     * @param name      The pet's name
     * @param gender    The pet's gender (i.e. male, female)
     * @param timestamp The pet's creation timestamp
     * @return the result code
     * @throws IOException
     */
    public int createPet(String type, String name, Pet.Gender gender, String timestamp) throws IOException {
        if (type != null && name != null && gender != null && timestamp != null) {
            return new CreatePetRequest(client, baseUrl, type, name, gender.getValue(), timestamp).execute();
        } else {
            throw new IllegalArgumentException("Arguments must not be null");
        }
    }

    /**
     * This action allows to search a pet by name.
     * @param name The searched name
     * @return found pets
     * @throws IOException
     */
    public List<Pet> searchByName(String name) throws IOException {
        if (name != null) {
            return new SearchPetByNameRequest(client, baseUrl, name).execute();
        } else {
            throw new IllegalArgumentException("Name must not be null");
        }
    }

    /**
     * This action allows to search a pet by type.
     * @param type The searched type
     * @return found pets
     * @throws IOException
     */
    public List<Pet> searchByType(String type) throws IOException {
        if (type != null) {
            return new SearchPetByTypeRequest(client, baseUrl, type).execute();
        } else {
            throw new IllegalArgumentException("Type must not be null");
        }
    }

    /**
     * This action allows to search a pet by type.
     * @param type   The searched type
     * @param gender The searched gender
     * @return found pets
     * @throws IOException
     */
    public List<Pet> searchByTypeAndGender(String type, Pet.Gender gender) throws IOException {
        if (type != null && gender != null) {
            return new SearchPetByTypeAndGenderRequest(client, baseUrl, type, gender).execute();
        } else {
            throw new IllegalArgumentException("Arguments must not be null");
        }
    }

    /**
     * This action allows to delete a pet with the given id
     * @param id The pet's id
     * @return the result code
     * @throws IOException
     */
    public int deletePet(long id) throws IOException {
        return new DeletePetRequest(client, baseUrl, id).execute();
    }
}
