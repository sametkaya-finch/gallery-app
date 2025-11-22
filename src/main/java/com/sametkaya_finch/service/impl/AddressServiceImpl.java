package com.sametkaya_finch.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.dto.DtoAddress;
import com.sametkaya_finch.dto.DtoAddressIU;
import com.sametkaya_finch.entity.Address;
import com.sametkaya_finch.repository.AddressRepository;
import com.sametkaya_finch.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressRepository addressRepository;

	private Address createAddres(DtoAddressIU dtoAddressIU) {
		Address address = new Address();
		address.setCreateTimeDate(new Date());

		BeanUtils.copyProperties(dtoAddressIU, address);

		return address;
	}

	@Override
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		Address savedAddress = addressRepository.save(createAddres(dtoAddressIU));

		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(savedAddress, dtoAddress);
		return dtoAddress;
	}

}
