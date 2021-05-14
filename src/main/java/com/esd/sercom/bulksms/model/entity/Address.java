package com.esd.sercom.bulksms.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bulksms_User_Address")
public class Address {
    @Id
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
}
