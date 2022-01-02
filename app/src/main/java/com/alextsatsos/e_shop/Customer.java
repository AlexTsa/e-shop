package com.alextsatsos.e_shop;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
// Δημιουργία πίνακα στην βάση με όνομα customer και πέδια : cid(κύριο κλειδί),email,lastName,firstName,city,address.
@Entity(tableName = "customers")
public class Customer {
    @PrimaryKey
    private int cid;

    private String email;

    private String lastName;

    private String firstName;

    private String city;

    private String address;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
