package com.example.test;

import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private byte[] photo;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;

    // Constructors
    public Employee() {
        // Default constructor
    }

    public Employee(String lastName, String firstName, String phoneNumber, String email,byte[] photo) {
        this.id = id;
        this.photo = photo;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Employee(int id, String lastName, String firstName, String phoneNumber, String email) {
        this.id = id;
        this.photo = photo;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Employee(int id, String lastName, String firstName, String phoneNumber, String email,byte [] photo) {
        this.id = id;
        this.photo = photo;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public Employee(String lastName, String firstName, String phoneNumber, String email) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
