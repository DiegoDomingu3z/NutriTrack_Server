package com.nutritrack.client.models;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "uid", nullable = false)
    private String uid;  // Firebase UID will be set directly

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "logged_in")
    private boolean loggedIn;

//    @Column(name = "password_hash", nullable = false)
//    private String password_hash;

//    @Column(name = "created_on", updatable = false, insertable = false)
//    private LocalDateTime created_on;

//    @Column
//    private String firstName;
//
//    @Column
//    private String lastName;

    // Getters and setters
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "Account{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}
