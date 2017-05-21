package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * The ConsoleClientApp class.
 */
public class ConsoleClientApp {

    /* constants for commands. */
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String GENDER = "gender";
    private static final String DELETE = "delete";

    private final PetManagementSystemClient petManagementSystemClient;

    /**
     * Creates a new instance of ConsoleClientApp.
     * @param petManagementSystemClient the petManagementSystemClient reference
     */
    public ConsoleClientApp(PetManagementSystemClient petManagementSystemClient) {
        this.petManagementSystemClient = petManagementSystemClient;
    }

    /**
     * Processes the given CSV file for creating pets.
     * @param csv the CSV file with data about pets
     */
    public void processCsv(String csv) {
        try (Stream<String> stream = Files.lines(Paths.get(csv))) {
            stream.forEach(line -> {
                String[] attributes = line.split(",");
                try {
                    petManagementSystemClient.createPet(attributes[0],
                                                        attributes[1],
                                                        Pet.Gender.getGenderByLetter(attributes[2]).orElse(null),
                                                        attributes[3]);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Processes commands from the console.
     * @param command1 first command
     * @param command2 second command
     * @throws IOException
     */
    public void processCommands(Command command1, Command command2) throws IOException {
        List<Pet> pets = null;
        switch (command1.getName()) {
            case NAME:
                pets = petManagementSystemClient.searchByName(command1.getValue());
                System.out.println("Found pets:");
                pets.forEach(System.out::println);
                break;
            case TYPE:
                if (command2 != null) {
                    if (command2.getName().equals(GENDER)) {
                        pets = petManagementSystemClient.searchByTypeAndGender(command1.getValue(),
                                                                               Pet.Gender.getGenderByValue(command2.getValue())
                                                                                         .orElse(null));
                    }
                } else {
                    pets = petManagementSystemClient.searchByType(command1.getValue());
                }
                if (pets != null) {
                    System.out.println("Found pets:");
                    pets.forEach(System.out::println);
                } else {
                    System.out.println("Invalid command");
                }
                break;
            case DELETE:
                petManagementSystemClient.deletePet(Long.parseLong(command1.getValue()));
                System.out.println("Pet deleted");
                break;
            default:
                System.out.println("Invalid command");
        }
    }
}
