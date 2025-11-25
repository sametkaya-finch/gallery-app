package com.sametkaya_finch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.controller.impl.RestAddressControllerImpl;
import com.sametkaya_finch.dto.DtoAddress;
import com.sametkaya_finch.dto.DtoAddressIU;
import com.sametkaya_finch.entity.Address;
import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;
import com.sametkaya_finch.repository.AddressRepository;
import com.sametkaya_finch.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

	private final RestAddressControllerImpl restAddressControllerImpl;

	@Autowired
	private AddressRepository addressRepository;

	AddressServiceImpl(RestAddressControllerImpl restAddressControllerImpl) {
		this.restAddressControllerImpl = restAddressControllerImpl;
	}

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

	@Override
	public DtoAddress getAddressById(Long id) {
		Optional<Address> optAddress = addressRepository.findById(id);
		if (optAddress.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Address address = optAddress.get();
		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(address, dtoAddress);

		return dtoAddress;
	}

	@Override
	public List<DtoAddress> getAllAddresses() {

		List<Address> addresses = addressRepository.findAll();
		List<DtoAddress> dtoAddressesList = new ArrayList<>();

		for (Address address : addresses) {
			DtoAddress dtoAddress = new DtoAddress();
			BeanUtils.copyProperties(addresses, dtoAddress);
			dtoAddressesList.add(dtoAddress);

		}

		return dtoAddressesList;
	}

	@Override
	public DtoAddress uptadeAddress(Long id, DtoAddressIU dtoAddressIU) {
		Optional<Address> optAddress = addressRepository.findById(id);
		if (optAddress.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Address address = optAddress.get();
		BeanUtils.copyProperties(dtoAddressIU, address, "id", "CreateTimeDate");

		Address updatedAddress = addressRepository.save(address);

		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(updatedAddress, dtoAddress);

		return dtoAddress;
	}

	@Override
	public void deleteAddress(Long id) {
		Optional<Address> optAddress = addressRepository.findById(id);
		if (optAddress.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Address address = optAddress.get();

		addressRepository.delete(address);
	}

}
