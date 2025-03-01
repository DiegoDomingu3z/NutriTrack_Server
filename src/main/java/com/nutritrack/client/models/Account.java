package com.nutritrack.client.models;

import jakarta.persistence.*;

/**
 * @author Diego Dominguez
 * @date: Feb 16 2025
 * @version 1.0
 * @description: Entity representing a user account. Each account is uniquely identified by
 *               a Firebase UID and includes an email and login status.
 */
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "uid", nullable = false, updatable = false)
    private String uid;  // Firebase UID will be set directly

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "logged_in", nullable = false)
    private boolean loggedIn;

    /**
     * Gets the unique identifier (UID) of the account.
     *
     * @return String The Firebase UID associated with the account.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the unique identifier (UID) of the account.
     *
     * @param uid The Firebase UID to be set.
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Gets the email associated with the account.
     *
     * @return String The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email associated with the account.
     *
     * @param email The email to be set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Checks whether the user is currently logged in.
     *
     * @return boolean True if the user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the login status of the user.
     *
     * @param loggedIn The login status to be set (true for logged in, false for logged out).
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Returns a string representation of the Account object.
     *
     * @return String A formatted string containing account details.
     */
    @Override
    public String toString() {
        return "Account{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}
