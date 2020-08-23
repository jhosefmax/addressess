package com.stoomtest.addresses.api;

import com.stoomtest.addresses.model.Address;
import com.stoomtest.addresses.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/address")
@RestController
public class AddressController {

    private final AddressService _addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this._addressService = addressService;
    }

    /**
     * Get all address
     * @return List of address
     */
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddress(){
        try {
            List<Address> lstAddress = _addressService.getAllAddress();

            if(lstAddress.isEmpty())
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity(lstAddress, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get address by ID
     * @param id Address Id
     * @return Unique Address
     */
    @GetMapping(path="{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") String id){
        try {
            Optional<Address> address = _addressService.getAddressById(id);

            if(address.isPresent())
                return new ResponseEntity(address.get(), HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Insert new address
     * @param address Address information to insert
     * @return Address inserted
     */
    @PostMapping
    public ResponseEntity addAddress(@Valid @RequestBody Address address){
        try{
            Address result = _addressService.addAddress(address);
            if(result != null)
                return new ResponseEntity(result, HttpStatus.CREATED);
            else
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update an address
     * @param id Address Id
     * @param address Address information to update
     * @return Address updated
     */
    @PutMapping(path="{id}")
    public ResponseEntity updateAddress(@NotEmpty @PathVariable("id") String id, @Valid @RequestBody Address address){
        try{
            Address result = _addressService.updateAddress(id, address);
            if(result != null)
                return new ResponseEntity(result, HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete an address
     * @param id Address Id to delete
     * @return 1: Deleted - 0: Not deleted
     */
    @DeleteMapping(path="{id}")
    public ResponseEntity deleteAddress(@NotEmpty @PathVariable("id") String id){
        try {
            int result = _addressService.deleteAddress(id);
            if(result == 1)
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity(HttpStatus.NOT_FOUND);

        }catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
