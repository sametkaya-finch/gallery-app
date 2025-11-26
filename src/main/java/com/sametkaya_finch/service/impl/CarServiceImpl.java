package com.sametkaya_finch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.dto.DtoCar;
import com.sametkaya_finch.dto.DtoCarIU;
import com.sametkaya_finch.entity.Car;
import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;
import com.sametkaya_finch.repository.CarRepository;
import com.sametkaya_finch.service.ICarService;

@Service
public class CarServiceImpl implements ICarService {

	@Autowired
	private CarRepository carRepository;

	private Car createCar(DtoCarIU dtoCarIU) {
		Car car = new Car();
		car.setCreateTimeDate(new Date());

		BeanUtils.copyProperties(dtoCarIU, car);

		return car;
	}

	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		Car savedCar = carRepository.save(createCar(dtoCarIU));

		DtoCar dtoCar = new DtoCar();

		BeanUtils.copyProperties(savedCar, dtoCar);

		return dtoCar;

	}

	@Override
	public DtoCar getCarById(Long id) {
		Optional<Car> optCar = carRepository.findById(id);
		if (optCar.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Car car = optCar.get();
		DtoCar dtoCar = new DtoCar();
		BeanUtils.copyProperties(car, dtoCar);

		return dtoCar;
	}

	@Override
	public List<DtoCar> getAllCars() {
		List<Car> cars = carRepository.findAll();
		List<DtoCar> dtoCarList = new ArrayList<>();

		for (Car car : cars) {
			DtoCar dtoCar = new DtoCar();
			BeanUtils.copyProperties(car, dtoCar);
			dtoCarList.add(dtoCar);
		}

		return dtoCarList;
	}

	@Override
	public DtoCar updateCar(Long id, DtoCarIU dtoCarIU) {
		Optional<Car> optCar = carRepository.findById(id);
		if (optCar.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Car car = optCar.get();
		BeanUtils.copyProperties(dtoCarIU, car);
		Car updatedCar = carRepository.save(car);

		DtoCar dtoCar = new DtoCar();
		BeanUtils.copyProperties(updatedCar, dtoCar);

		return dtoCar;
	}

	@Override
	public void deleteCar(Long id) {
		Optional<Car> optCar = carRepository.findById(id);
		if (optCar.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Car car = optCar.get();

		carRepository.delete(car);
	}

}
