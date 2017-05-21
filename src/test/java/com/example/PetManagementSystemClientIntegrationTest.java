package com.example;

import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PetManagementSystemClientIntegrationTest {

    private static final int OK = 200;
    private static final String ADDRESS = "localhost";
    private static final short PORT = 9000;

    private PetManagementSystemClient petManagementSystemClient = new PetManagementSystemClient(ADDRESS, PORT);

    private static final BinaryOperator<Pet> COMPARING_BY_TIMESTAMP = (pet1, pet2) -> pet1.getTimestamp().after(pet2.getTimestamp()) ? pet1 : pet2;
    private static final BinaryOperator<Pet> COMPARING_BY_NAME = (pet1, pet2) -> pet1.getName().compareTo(pet2.getName()) < 0 ? pet1 : pet2;

    private Predicate<Pet> filteringByTimestamp(List<Pet> pets) {
        return pet -> pet.getTimestamp().equals(pets.get(0).getTimestamp());
    }

    private Predicate<Pet> filteringByName(List<Pet> pets) {
        return pet -> pet.getName().equals(pets.get(0).getName());
    }

    private boolean isInOrder(List<Pet> pets, BinaryOperator<Pet> comparingFunction, Predicate<Pet> filteringFunction) {
        return pets.stream()
                   .reduce(comparingFunction)
                   .filter(filteringFunction)
                   .isPresent();
    }

    @Test
    public void testCreatePet() throws IOException {
        int resultCode = petManagementSystemClient.createPet("dog", "Spike", Pet.Gender.MALE);
        assertEquals(OK, resultCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreatePetWithNullParameters() throws IOException {
        petManagementSystemClient.createPet(null, null, null);
    }

    @Test
    public void testSearchPetByName() throws IOException {
        List<Pet> pets = petManagementSystemClient.searchByName("spike");
        if (!pets.isEmpty()) {
            assertTrue(pets.stream().allMatch(pet -> pet.getName().toLowerCase().contains("spike")));
            assertTrue(isInOrder(pets, COMPARING_BY_NAME, filteringByName(pets)));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchPetByNameWithNullName() throws IOException {
        petManagementSystemClient.searchByName(null);
    }

    @Test
    public void testSearchPetByType() throws IOException {
        List<Pet> pets = petManagementSystemClient.searchByType("dog");
        if (!pets.isEmpty()) {
            assertTrue(pets.stream().allMatch(pet -> pet.getType().toLowerCase().contains("dog")));
            assertTrue(isInOrder(pets, COMPARING_BY_TIMESTAMP, filteringByTimestamp(pets)));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchByTypeeWithNullType() throws IOException {
        petManagementSystemClient.searchByType(null);

    }

    @Test
    public void testSearchPetByTypeAndGender() throws IOException {
        List<Pet> pets = petManagementSystemClient.searchByTypeAndGender("dog", Pet.Gender.MALE);
        if (!pets.isEmpty()) {
            assertTrue(pets.stream().allMatch(pet -> pet.getType().toLowerCase().contains("dog") && pet.getGender().toLowerCase().contains("male")));
            assertTrue(isInOrder(pets, COMPARING_BY_TIMESTAMP, filteringByTimestamp(pets)));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchPetByTypeAndGenderhNullType() throws IOException {
        petManagementSystemClient.searchByTypeAndGender(null, null);

    }
}
