package com.stoomtest.addresses.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Document(collection = "addresses")
public class Address {

    @Id
    private String id;
    @NotBlank(message="Street Name must be informed")
    private String streetName;
    @NotNull(message="Number must be informed")
    private Number number;
    private String complement;
    @NotBlank(message="Neighbourhood must be informed")
    private String neighbourhood;
    @NotEmpty(message = "City may not be empty")
    private String city;
    @NotBlank(message="State must be informed")
    private String state;
    @NotBlank(message="Country must be informed")
    private String country;
    @NotBlank(message="ZipCode must be informed")
    private String zipcode;
    private Number latitude;
    private Number longitude;

    public Address(){ }

    public Address(String id,
                   String streetName,
                   java.lang.Number number,
                   String complement,
                   String neighbourhood,
                   String city,
                   String state,
                   String country,
                   String zipcode,
                   java.lang.Number latitude,
                   java.lang.Number longitude) {
        this.id = id;
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Number getLatitude() {
        return latitude;
    }

    public void setLatitude(Number latitude) {
        this.latitude = latitude;
    }

    public Number getLongitude() {
        return longitude;
    }

    public void setLongitude(Number longitude) {
        this.longitude = longitude;
    }
}
