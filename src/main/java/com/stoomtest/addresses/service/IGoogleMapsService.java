package com.stoomtest.addresses.service;

import com.google.maps.model.GeocodingResult;

public interface IGoogleMapsService {
    public GeocodingResult getGeoCoding(String completeAddress);
}
