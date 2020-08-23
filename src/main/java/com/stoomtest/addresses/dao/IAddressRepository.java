package com.stoomtest.addresses.dao;

import com.stoomtest.addresses.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAddressRepository extends MongoRepository<Address, String> {
}
