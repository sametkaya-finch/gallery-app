package com.sametkaya_finch.controller;

import java.util.List;

import com.sametkaya_finch.dto.DtoCar;
import com.sametkaya_finch.dto.DtoCarIU;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

	public RootEntity<DtoCar> getCarById(Long id);

	public RootEntity<List<DtoCar>> getAllCars();

	public RootEntity<DtoCar> updateCar(Long id, DtoCarIU dtoCarIU);

	public RootEntity<String> deleteCar(Long id);

}
