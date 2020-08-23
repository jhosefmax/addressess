package com.stoomtest.addresses;

import com.stoomtest.addresses.dao.IAddressRepository;
import com.stoomtest.addresses.model.Address;
import com.stoomtest.addresses.service.AddressService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressesApplicationTests {

	@Autowired
	private AddressService addressService;

	private Address address1;
	private Address address2;
	private List<Address> lstAddress;

	@Before
	public void setup(){
		address1 = new Address("5f41c2cb9ab6852f1978a0e0",
				"Luiza Nascimbene",
				1160,
				"CASA A",
				"Vila Celeste",
				"Ipatinga",
				"Minas Gerais",
				"Brasil",
				"35162507",
				-19.4564102,
				-42.5572598);

		address2 = new Address("5f41ef1d6bebda56e7651bd8",
				"Nelsia Vannucci",
				105,
				"Dalia 126",
				"Jardim Nova Europa",
				"Campinas",
				"São Paulo",
				"Brasil",
				"35162507",
				-22.930817,
				-47.0546861);
		lstAddress = new ArrayList<>();
		lstAddress.add(address1);
		lstAddress.add(address2);
	}

	@MockBean
	private IAddressRepository addressRepository;

	@Test
	public void getAllAddressTest(){
		//Arrange
		when(addressRepository.findAll()).thenReturn(lstAddress);

		//Act
		List<Address> lstAddressResult = addressService.getAllAddress();

		//Assert
		Assert.assertNotNull(lstAddressResult);
		Assert.assertEquals(2, lstAddressResult.size());
	}

	@Test
	public void getAddressByIdTest(){
		//Arrange
		String id = "5f41c2cb9ab6852f1978a0e0";
		when(addressRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(address1));

		//Act
		Address addressResult = addressService.getAddressById(id).orElse(null);

		//Assert
		Assert.assertNotNull(addressResult);
		Assert.assertEquals(id, addressResult.getId());
		Assert.assertEquals("Luiza Nascimbene", addressResult.getStreetName());
	}

	@Test
	public void addAddressTest(){
		//Arrange
		Address newAddress = address1;
		when(addressRepository.insert(newAddress)).thenReturn(address1);

		//Act
		newAddress.setLongitude(null);
		newAddress.setLatitude(null);
		Address addressResult = addressService.addAddress(newAddress);

		//Assert
		Assert.assertNotNull(addressResult);
		Assert.assertEquals(-19.4564102, addressResult.getLatitude());
		Assert.assertEquals(-42.5572598, addressResult.getLongitude());
	}

	@Test
	public void updateAddressTest(){
		//Arrange
		String id = "5f41ef1d6bebda56e7651bd8";
		Address newAddress = address2;
		when(addressRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(address2));
		when(addressRepository.save(newAddress)).thenReturn(address2);

		//Act
		newAddress.setLongitude(null);
		newAddress.setLatitude(null);
		Address addressResult = addressService.updateAddress(id, newAddress);

		//Assert
		Assert.assertNotNull(addressResult);
		Assert.assertEquals("Nelsia Vannucci", addressResult.getStreetName());
		Assert.assertEquals("São Paulo", addressResult.getState());
	}

	@Test
	public void deleteAddressTest(){
		//Arrange
		String id = "5f4174c7adcd985f76b4a367";
		when(addressRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(address1));

		//Act
		int result = addressService.deleteAddress(id);

		//Assert
		verify(addressRepository, times(1)).deleteById(id);
	}


	@Test
	public void contextLoads() {
	}

}