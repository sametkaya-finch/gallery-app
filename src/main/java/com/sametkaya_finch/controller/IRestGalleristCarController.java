package com.sametkaya_finch.controller;

import java.util.List;

import com.sametkaya_finch.dto.DtoGalleristCar;
import com.sametkaya_finch.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

	public RootEntity<DtoGalleristCar> getGalleristCarById(Long id);

	public RootEntity<List<DtoGalleristCar>> getAllGalleristCars();

	public RootEntity<DtoGalleristCar> updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU);

	public RootEntity<String> deleteGalleristCar(Long id);

}
