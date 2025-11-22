package com.sametkaya_finch.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.config.SecurityConfig;
import com.sametkaya_finch.dto.DtoAddress;
import com.sametkaya_finch.dto.DtoGallerist;
import com.sametkaya_finch.dto.DtoGalleristIU;
import com.sametkaya_finch.entity.Address;
import com.sametkaya_finch.entity.Gallerist;
import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;
import com.sametkaya_finch.repository.AddressRepository;
import com.sametkaya_finch.repository.GalleristRepository;
import com.sametkaya_finch.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService {

	private final SecurityConfig securityConfig;

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private AddressRepository addressRepository;

	GalleristServiceImpl(SecurityConfig securityConfig) {
		this.securityConfig = securityConfig;
	}

	private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {

		Gallerist gallerist = new Gallerist();
		gallerist.setCreateTimeDate(new Date());

		BeanUtils.copyProperties(dtoGalleristIU, gallerist);

		// finbyid address donuyor opt icinde address tipinde address var
		Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
		if (optAddress.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
		}

		gallerist.setAddress(optAddress.get());

		return gallerist;

	}

	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {

		Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));

		DtoGallerist dtoGallerist = new DtoGallerist();

		BeanUtils.copyProperties(savedGallerist, dtoGallerist);

		DtoAddress dtoAddress = new DtoAddress();

		BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);

		dtoGallerist.setAddress(dtoAddress);

		return dtoGallerist;
	}

}
