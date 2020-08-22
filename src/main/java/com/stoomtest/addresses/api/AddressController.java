package com.stoomtest.addresses.api;

import com.stoomtest.addresses.model.Address;
import com.stoomtest.addresses.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/address")
@RestController
public class AddressController {

    private final AddressService _addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this._addressService = addressService;
    }
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddress(){
        List<Address> lstAddress = new ArrayList<>();
        try {
            lstAddress = _addressService.getAllAddress();

            if(lstAddress.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(lstAddress, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path="{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") UUID idAddress){
        Optional<Address> address;

        try {
            address = _addressService.getAddressById(idAddress);

            if(address.isPresent())
                return new ResponseEntity<>(address.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity addAddress(@RequestBody Address address){
        try{
            Address result = _addressService.addAddress(address);
            if(result != null)
                return new ResponseEntity(HttpStatus.CREATED);
            else
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path="{id}")
    public ResponseEntity updateAddress(@PathVariable("id") UUID idAddress, @RequestBody Address address){
        try{
            Address result = _addressService.updateAddress(idAddress, address);
            if(result != null)
                return new ResponseEntity(HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(path="{id}")
    public ResponseEntity deleteAddress(@PathVariable("id") UUID idAddress){
        try {
            int result = _addressService.deleteAddress(idAddress);
            if(result == 1)
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);

        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
