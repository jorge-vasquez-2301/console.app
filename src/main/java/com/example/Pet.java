package com.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Pet class.
 * This class models the information about a pet.
 * @author Jorge Vasquez
 * @since 1.8
 */
public class Pet {

    private long id;
    private String type;
    private String name;
    private String gender;
    private Date timestamp;

    /**
     * Empty constructor for Pet.
     */
    public Pet() { }

    /**
     * Creates a new instance of Pet.
     * @param id
     * @param type
     * @param name
     * @param gender
     * @param timestamp
     */
    public Pet(long id, String type, String name, String gender, Date timestamp) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.gender = gender;
        this.timestamp = timestamp;
    }

    /**
     * @return pet's id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the pet's id.
     * @param id new id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return pet's type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the pet's type.
     * @param type new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return pet's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the pet's name.
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return pet's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the pet's gender.
     * @param gender new gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return pet's timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the pet's timestamp.
     * @param timestamp new timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return String.format("id: %d, type: %s, name: %s, gender: %s, timestamp: %s",
                             id,
                             type,
                             name,
                             gender,
                             dateFormat.format(timestamp));
    }

    /**
     * The Gender enum.
     * This models the possible values for a pet's gender.
     */
    public enum Gender {
        MALE    ("male"),
        FEMALE  ("female");

        private String value;

        /**
         * Constructor for Gender.
         * @param value Gender value: male/female
         */
        Gender(String value) {
            this.value = value;
        }

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }
}
