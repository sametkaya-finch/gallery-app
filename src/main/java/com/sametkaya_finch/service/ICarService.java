package com.sametkaya_finch.service;

import java.util.List;

import com.sametkaya_finch.dto.DtoCar;
import com.sametkaya_finch.dto.DtoCarIU;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU dtoCarIU);

	public DtoCar getCarById(Long id);

	public List<DtoCar> getAllCars();

	public DtoCar updateCar(Long id, DtoCarIU dtoCarIU);

	public void deleteCar(Long id);
}
