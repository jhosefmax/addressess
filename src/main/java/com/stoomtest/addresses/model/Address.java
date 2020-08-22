package com.stoomtest.addresses.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Address {

    private UUID Id;
    private final String StreetName;
    private final Number Number;
    private final String Complement;
    private final String Neighbourhood;
    private final String City;
    private final String State;
    private final String Country;
    private final String Zipcode;
    private final String Latitude;
    private final String Longitude;


    public Address(@JsonProperty("id") UUID id,
                   @JsonProperty("streetName") String streetName,
                   @JsonProperty("number") java.lang.Number number,
                   @JsonProperty("complement") String complement,
                   @JsonProperty("neighbourhood") String neighbourhood,
                   @JsonProperty("city") String city,
                   @JsonProperty("state") String state,
                   @JsonProperty("country") String country,
                   @JsonProperty("zipcode") String zipcode,
                   @JsonProperty("latitude") String latitude,
                   @JsonProperty("longitude") String longitude) {
        Id = id;
        StreetName = streetName;
        Number = number;
        Complement = complement;
        Neighbourhood = neighbourhood;
        City = city;
        State = state;
        Country = country;
        Zipcode = zipcode;
        Latitude = latitude;
        Longitude = longitude;
    }

    public UUID getId() {
        return Id;
    }
    public void setId(UUID id) {
        this.Id = id;
    }

    public String getStreetName() {
        return StreetName;
    }

    public java.lang.Number getNumber() {
        return Number;
    }

    public String getComplement() {
        return Complement;
    }

    public String getNeighbourhood() {
        return Neighbourhood;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getCountry() {
        return Country;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }
}
