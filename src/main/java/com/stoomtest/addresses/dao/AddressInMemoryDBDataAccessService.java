package com.stoomtest.addresses.dao;

import com.stoomtest.addresses.model.Address;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("InMemoryDao")
public abstract class AddressInMemoryDBDataAccessService implements IAddressDao {
    private static List<Address> DB = new ArrayList<>();

    @Override
    public List<Address> getAllAddress() {
        return DB;
    }

    @Override
    public Optional<Address> getAddressById(UUID idAddress) {
        return DB.stream()
                .filter(address -> address.getId().equals(idAddress))
                .findFirst();
    }

    @Override
    public Address addOrUpdateAddress(Address address) {
        DB.add(address);
        return address;
    }

    @Override
    public int deleteAddress(UUID idAddress) {
        Optional<Address> addressExistent = getAddressById(idAddress);

        if (addressExistent.isPresent()) {
            DB.remove(addressExistent.get());
            return 1;
        } else
            return 0;
    }
}