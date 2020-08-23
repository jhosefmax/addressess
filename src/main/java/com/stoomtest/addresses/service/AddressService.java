package com.stoomtest.addresses.service;

import com.stoomtest.addresses.dao.IAddressRepository;
import com.stoomtest.addresses.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.maps.model.GeocodingResult;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.UnsupportedEncodingException;


@Service
public class AddressService implements IAddressService{

    private final IAddressRepository _addressRepository;
    private final IGoogleMapsService _mapsService;

    @Autowired
    public AddressService(IAddressRepository addressDao, IGoogleMapsService mapsService){
        this._addressRepository = addressDao;
        this._mapsService = mapsService;
    }
    public List<Address> getAllAddress(){
        List<Address> lstAddress = new ArrayList<>();
        _addressRepository.findAll().forEach(lstAddress::add);
        return lstAddress;
    }
    public Optional<Address> getAddressById(String id){
        Optional<Address> address = _addressRepository.findById(id);
        return address;
    }
    public Address addAddress(Address address){
        if(address.getLatitude() == null || address.getLongitude() == null){
            this.fillCoordinates(address);
        }
        return _addressRepository.insert(address);
    }
    public Address updateAddress(String id, Address address){
        Optional<Address> addressResult = this.getAddressById(id);
        if(addressResult.isPresent()){
            if(address.getLatitude() == null || address.getLongitude() == null){
                this.fillCoordinates(address);
            }
            address.setId(id);
            return _addressRepository.save(address);
        }
        return null;
    }
    public int deleteAddress(String id){
        Optional<Address> addressExistent = this.getAddressById(id);
        if(addressExistent.isPresent()){
            _addressRepository.deleteById(id);
            return 1;
        }
        else
            return 0;
    }

    private void fillCoordinates(Address address){
        try {
            String completeAddress = String.format("%s, %s - %s - %s - %s",
                    address.getStreetName(),
                    address.getNumber(),
                    address.getNeighbourhood(),
                    address.getCity(),
                    address.getState());

            String encodedAddress = URLEncoder.encode(completeAddress, StandardCharsets.UTF_8.toString());

            GeocodingResult result = _mapsService.getGeoCoding(encodedAddress);

            if(result != null){
                address.setLatitude(result.geometry.location.lat);
                address.setLongitude(result.geometry.location.lng);
            }

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
