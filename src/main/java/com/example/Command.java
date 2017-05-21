package com.example;

/**
 * The Command class.
 * This class models a console command.
 */
public class Command {

    private final String name;
    private final String value;

    /**
     * Creates a new instance of Command.
     * @param name  Command's name
     * @param value Command's value
     */
    public Command(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
