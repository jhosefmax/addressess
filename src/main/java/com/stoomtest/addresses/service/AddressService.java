package com.stoomtest.addresses.service;

import com.stoomtest.addresses.dao.IAddressDao;
import com.stoomtest.addresses.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService implements IAddressService{

    private final IAddressDao _addressDao;

    @Autowired
    public AddressService(@Qualifier("MongoDBDao") IAddressDao addressDao){
        this._addressDao = addressDao;
    }
    public List<Address> getAllAddress(){
        return _addressDao.getAllAddress();
    }
    public Optional<Address> getAddressById(UUID idAddress){
        return _addressDao.getAddressById(idAddress);
    }
    public Address addAddress(Address address){
        address.setId(UUID.randomUUID());
        return _addressDao.addOrUpdateAddress(address);
    }
    public Address updateAddress(UUID idAddress, Address address){
        Optional<Address> addressResult = this.getAddressById(idAddress);
        if(addressResult.isPresent()){
            address.setId(idAddress);
            return _addressDao.addOrUpdateAddress(address);
        }
        return null;
    }
    public int deleteAddress(UUID idAddress){
        Optional<Address> addressExistent = getAddressById(idAddress);
        if(addressExistent.isPresent()){
            _addressDao.deleteAddress(idAddress);
            return 1;
        }
        else
            return 0;
    }
}
