package com.nutritrack.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * @author Diego Dominguez
 * @date: Feb 16 2025
 * @version 1.0
 * @description: Data Transfer Object (DTO) for handling user account creation requests.
 *               This DTO encapsulates account details including UID, email, and profile information.
 */
public class CreateAccountRequestDTO {
    private String uid;
    private String email;
    private ProfileDTO profile;

    /**
     * Gets the user’s unique identifier (UID).
     *
     * @return String The UID of the user.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the user’s unique identifier (UID).
     *
     * @param uid The UID of the user.
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Gets the user's email.
     *
     * @return String The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the profile details of the user.
     *
     * @return ProfileDTO The user's profile information.
     */
    public ProfileDTO getProfile() {
        return profile;
    }

    /**
     * Sets the profile details of the user.
     *
     * @param profile The user's profile information.
     */
    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
    }

    /**
     * @description: Nested DTO class representing the profile details of the user.
     */
    public static class ProfileDTO {
        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        @JsonProperty("date_of_birth")
        private String dateOfBirth;

        private String gender;
        private BigDecimal height;
        private BigDecimal weight;

        /**
         * Gets the user's first name.
         *
         * @return String The first name of the user.
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Sets the user's first name.
         *
         * @param firstName The first name of the user.
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * Gets the user's last name.
         *
         * @return String The last name of the user.
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * Sets the user's last name.
         *
         * @param lastName The last name of the user.
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         * Gets the user's date of birth.
         *
         * @return String The date of birth of the user.
         */
        public String getDateOfBirth() {
            return dateOfBirth;
        }

        /**
         * Sets the user's date of birth.
         *
         * @param dateOfBirth The date of birth of the user.
         */
        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        /**
         * Gets the user's gender.
         *
         * @return String The gender of the user.
         */
        public String getGender() {
            return gender;
        }

        /**
         * Sets the user's gender.
         *
         * @param gender The gender of the user.
         */
        public void setGender(String gender) {
            this.gender = gender;
        }

        /**
         * Gets the user's height.
         *
         * @return BigDecimal The height of the user.
         */
        public BigDecimal getHeight() {
            return height;
        }

        /**
         * Sets the user's height.
         *
         * @param height The height of the user.
         */
        public void setHeight(BigDecimal height) {
            this.height = height;
        }

        /**
         * Gets the user's weight.
         *
         * @return BigDecimal The weight of the user.
         */
        public BigDecimal getWeight() {
            return weight;
        }

        /**
         * Sets the user's weight.
         *
         * @param weight The weight of the user.
         */
        public void setWeight(BigDecimal weight) {
            this.weight = weight;
        }
    }
}
