package com.sametkaya_finch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.dto.DtoAddress;
import com.sametkaya_finch.dto.DtoCar;
import com.sametkaya_finch.dto.DtoGallerist;
import com.sametkaya_finch.dto.DtoGalleristCar;
import com.sametkaya_finch.dto.DtoGalleristCarIU;
import com.sametkaya_finch.entity.Car;
import com.sametkaya_finch.entity.Gallerist;
import com.sametkaya_finch.entity.GalleristCar;
import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;
import com.sametkaya_finch.repository.CarRepository;
import com.sametkaya_finch.repository.GalleristCarRepository;
import com.sametkaya_finch.repository.GalleristRepository;
import com.sametkaya_finch.service.IGalleristCarService;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

	@Autowired
	private GalleristCarRepository galleristCarRepository;

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private CarRepository carRepository;

	private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreateTimeDate(new Date());

		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		if (optGallerist.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
		}

		Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
		}

		galleristCar.setGallerist(optGallerist.get());
		galleristCar.setCar(optCar.get());

		return galleristCar;

	}

	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();

		// Gallerist icinde address var o address kopyalanmaz
		DtoAddress dtoAddress = new DtoAddress();

		BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);

		dtoGallerist.setAddress(dtoAddress);

		BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);

		BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);

		dtoGalleristCar.setGallerist(dtoGallerist);
		dtoGalleristCar.setCar(dtoCar);

		// id ve createtime kopyalandi
		BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);

		return dtoGalleristCar;

	}

	@Override
	public DtoGalleristCar getGalleristCarById(Long id) {
		Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);
		if (optGalleristCar.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		GalleristCar galleristCar = optGalleristCar.get();
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		BeanUtils.copyProperties(galleristCar, dtoGalleristCar);

		DtoGallerist dtoGallerist = new DtoGallerist();
		BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);
		dtoGalleristCar.setGallerist(dtoGallerist);

		DtoCar dtoCar = new DtoCar();
		BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);
		dtoGalleristCar.setCar(dtoCar);

		return dtoGalleristCar;
	}

	@Override
	public List<DtoGalleristCar> getAllGalleristCars() {
		List<GalleristCar> optGalleristCar = galleristCarRepository.findAll();
		List<DtoGalleristCar> dtoGalleristCarsList = new ArrayList<>();

		for (GalleristCar galleristCar : optGalleristCar) {
			DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
			BeanUtils.copyProperties(galleristCar, dtoGalleristCar);

			DtoGallerist dtoGallerist = new DtoGallerist();
			BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);
			dtoGalleristCar.setGallerist(dtoGallerist);

			DtoCar dtoCar = new DtoCar();
			BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);
			dtoGalleristCar.setCar(dtoCar);

			dtoGalleristCarsList.add(dtoGalleristCar);

		}

		return dtoGalleristCarsList;
	}

	@Override
	public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU) {
		Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);
		if (optGalleristCar.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Optional<Gallerist> optGallerist = galleristRepository.findById(id);
		if (optGallerist.isEmpty()) {
			new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));

		}

		Optional<Car> optCar = carRepository.findById(id);
		if (optCar.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));

		}

		GalleristCar galleristCar = optGalleristCar.get();
		galleristCar.setCar(optCar.get());
		galleristCar.setGallerist(optGallerist.get());

		GalleristCar updatedGalleristCar = galleristCarRepository.save(galleristCar);

		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		BeanUtils.copyProperties(galleristCar, dtoGalleristCar);

		DtoGallerist dtoGallerist = new DtoGallerist();
		BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);
		dtoGalleristCar.setGallerist(dtoGallerist);

		DtoCar dtoCar = new DtoCar();
		BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);
		dtoGalleristCar.setCar(dtoCar);

		return dtoGalleristCar;
	}

	@Override
	public void deleteGalleristCar(Long id) {
		Optional<GalleristCar> optGalleristCar = galleristCarRepository.findById(id);
		if (optGalleristCar.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		GalleristCar galleristCar = optGalleristCar.get();

		galleristCarRepository.delete(galleristCar);

	}

}
