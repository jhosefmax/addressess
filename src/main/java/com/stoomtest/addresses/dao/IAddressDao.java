package com.stoomtest.addresses.dao;

import com.stoomtest.addresses.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAddressDao extends MongoRepository<Address, UUID> {
    List<Address> getAllAddress();
    Optional<Address> getAddressById(UUID idAddress);
    Address addOrUpdateAddress(Address address);
    int deleteAddress(UUID idAddress);
}
