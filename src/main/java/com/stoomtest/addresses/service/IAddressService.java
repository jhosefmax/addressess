package com.stoomtest.addresses.service;

import com.stoomtest.addresses.model.Address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAddressService {

    List<Address> getAllAddress();
    Optional<Address> getAddressById(UUID idAddress);
    Address addAddress(Address address);
    Address updateAddress(UUID idAddress, Address address);
    int deleteAddress(UUID idAddress);
}
