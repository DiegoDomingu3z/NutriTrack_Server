package com.nutritrack.client.dto;

public class AccountRequest {
    private String uid;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean logged_in;

    // Getters and setters
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
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

    public Boolean getLogged_in() {
        return logged_in;
    }

    public void setLogged_in(Boolean logged_in) {
        this.logged_in = logged_in;
    }
}
