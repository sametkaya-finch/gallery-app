package com.sametkaya_finch.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.dto.DtoCar;
import com.sametkaya_finch.dto.DtoCarIU;
import com.sametkaya_finch.entity.Car;
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

}
