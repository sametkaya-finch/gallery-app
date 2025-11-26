package com.sametkaya_finch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private AddressRepository addressRepository;

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

	@Override
	public DtoGallerist getGalleristById(Long id) {
		Optional<Gallerist> optGallerist = galleristRepository.findById(id);
		if (optGallerist.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Gallerist gallerist = optGallerist.get();
		DtoGallerist dtoGallerist = new DtoGallerist();
		BeanUtils.copyProperties(gallerist, dtoGallerist);

		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);

		dtoGallerist.setAddress(dtoAddress);

		return dtoGallerist;
	}

	@Override
	public List<DtoGallerist> getAllGallerists() {
		List<Gallerist> gallerists = galleristRepository.findAll();
		List<DtoGallerist> dtoGalleristsList = new ArrayList<>();

		for (Gallerist gallerist : gallerists) {
			DtoGallerist dtoGallerist = new DtoGallerist();
			BeanUtils.copyProperties(gallerists, dtoGalleristsList);

			DtoAddress dtoAddress = new DtoAddress();
			BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);

			dtoGallerist.setAddress(dtoAddress);

			dtoGalleristsList.add(dtoGallerist);

		}
		return dtoGalleristsList;
	}

	@Override
	public DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU) {
		Optional<Gallerist> optGallerist = galleristRepository.findById(id);
		if (optGallerist.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
		if (optAddress.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));

		}

		Gallerist gallerist = optGallerist.get();
		BeanUtils.copyProperties(dtoGalleristIU, gallerist);
		gallerist.setAddress(optAddress.get());

		Gallerist updatedGallerists = galleristRepository.save(gallerist);

		DtoGallerist dtoGallerist = new DtoGallerist();
		BeanUtils.copyProperties(updatedGallerists, dtoGallerist);

		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(updatedGallerists.getAddress(), dtoAddress);
		dtoGallerist.setAddress(dtoAddress);

		return dtoGallerist;
	}

	@Override
	public void deleteGallerist(Long id) {
		Optional<Gallerist> optGallerist = galleristRepository.findById(id);
		if (optGallerist.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Gallerist gallerist = optGallerist.get();

		galleristRepository.delete(gallerist);

	}

}
