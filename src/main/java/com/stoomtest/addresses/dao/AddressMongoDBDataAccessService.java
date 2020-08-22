package com.stoomtest.addresses.dao;

import com.stoomtest.addresses.model.Address;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("MongoDBDao")
public abstract class AddressMongoDBDataAccessService implements IAddressDao {

    @Override
    public List<Address> getAllAddress(){
        List<Address> lstAddress = new ArrayList<>();
        this.findAll().forEach(lstAddress::add);
        return lstAddress;
    }
    @Override
    public Optional<Address> getAddressById(UUID idAddress){
        Optional<Address> address = this.findById(idAddress);
        return address;
    }

    @Override
    public Address addOrUpdateAddress(Address address){
        Address addressResult = this.save(address);
        return addressResult;
    }

    @Override
    public int deleteAddress(UUID idAddress){
        this.deleteById(idAddress);
        return 1;
    }
}
