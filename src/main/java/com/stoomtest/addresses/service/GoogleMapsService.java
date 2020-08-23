package com.stoomtest.addresses.service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleMapsService implements IGoogleMapsService {

    private String GOOGLE_API_KEY = "google.api.key";
    private GeoApiContext context;

    @Autowired
    public GoogleMapsService(Environment environment){
        context = new GeoApiContext.Builder()
                .apiKey(environment.getProperty(GOOGLE_API_KEY))
                .build();
    }


    public GeocodingResult getGeoCoding(String completeAddress){

        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, completeAddress).await();
            if(results != null && results.length > 0)
                return results[0];

        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
