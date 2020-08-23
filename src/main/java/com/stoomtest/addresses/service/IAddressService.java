package com.stoomtest.addresses.service;

import com.stoomtest.addresses.model.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressService {

    List<Address> getAllAddress();
    Optional<Address> getAddressById(String id);
    Address addAddress(Address address);
    Address updateAddress(String id, Address address);
    int deleteAddress(String id);
}
