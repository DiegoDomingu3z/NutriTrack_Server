package com.nutritrack.client.models;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import com.google.api.client.util.DateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * @author Diego Dominguez
 * @date: Feb 16 2025
 * @version 1.0
 * @description: Entity representing a user's profile, which is linked to an account.
 *               This class stores personal details such as name, date of birth,
 *               gender, height, weight, and preferred unit system.
 */
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;  // Unique profile ID generated as a UUID

    /**
     * Foreign key linking the profile to the corresponding account.
     */
    @OneToOne
    @JoinColumn(name = "account_uid", referencedColumnName = "uid", nullable = false)
    private Account account;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender = Gender.male;

    @Column(name = "height", precision = 4, scale = 1)
    private BigDecimal height; // Height measurement in cm or inches.

    @Column(name = "weight", precision = 4, scale = 1)
    private BigDecimal weight; // Weight measurement in kg or lbs.

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_system")
    private UnitSystem unitSystem = UnitSystem.metric; // User's measurement preference.

     @Column(name = "createdOn", nullable = false)
    private DateTime createdOn;

    @Column(name = "updatedOn", nullable = false)
    private DateTime updatedOn;


    /**
     * Default constructor for Profile.
     */
    public Profile() {
    }

    /**
     * Gets the unique ID of the profile.
     *
     * @return UUID The profile ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique ID of the profile.
     *
     * @param id The profile ID.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the associated account.
     *
     * @return Account The linked account entity.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the associated account.
     *
     * @param account The linked account entity.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Gets the first name of the user.
     *
     * @return String The user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The user's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return String The user's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The user's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the date of birth of the user.
     *
     * @return LocalDate The user's date of birth.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the user.
     *
     * @param dateOfBirth The user's date of birth.
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the gender of the user.
     *
     * @return Gender The gender of the user.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender of the user.
     *
     * @param gender The gender of the user.
     */
    public void setGender(String gender) {
        if (gender == null) {
            this.gender = null;
        } else {
            this.gender = Gender.valueOf(gender);
        }
    }

    /**
     * Gets the height of the user.
     *
     * @return BigDecimal The height of the user.
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * Sets the height of the user.
     *
     * @param height The height of the user.
     */
    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    /**
     * Gets the weight of the user.
     *
     * @return BigDecimal The weight of the user.
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the user.
     *
     * @param weight The weight of the user.
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * Gets the unit system preference of the user.
     *
     * @return UnitSystem The user's preferred measurement system.
     */
    public UnitSystem getUnitSystem() {
        return unitSystem;
    }

    /**
     * Sets the unit system preference of the user.
     *
     * @param unitSystem The user's preferred measurement system.
     */
    public void setUnitSystem(UnitSystem unitSystem) {
        this.unitSystem = unitSystem;
    }


    public DateTime getCreatedOn(){
        return createdOn;
    }

    public void setCreatedOn(DateTime date){
        this.createdOn = date;
    }

    public DateTime getUpdatedOn(){
        return updatedOn;
    }

    public void setUpdatedOn(DateTime date){
        this.updatedOn = date;
    }

    /**
     * Returns a string representation of the Profile object.
     *
     * @return String A formatted string containing profile details.
     */
    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", account=" + account +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", height=" + height +
                ", weight=" + weight +
                ", unitSystem=" + unitSystem +
                '}';
    }
}
