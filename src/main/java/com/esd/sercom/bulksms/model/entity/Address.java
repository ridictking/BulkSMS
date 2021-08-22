package com.esd.sercom.bulksms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Bulksms_User_Address")
public class Address {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String accountCode;
    private String streetAddress;
    private String City;
    private String State;
    private String country;
    public Address() {
    }

    public Address(String accountCode, String streetAddress, String city, String state, String country) {
        this.accountCode = accountCode;
        this.streetAddress = streetAddress;
        City = city;
        State = state;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getCountry() {
        return country;
    }
}
