package com.sametkaya_finch.service;

import java.util.List;

import com.sametkaya_finch.dto.DtoGalleristCar;
import com.sametkaya_finch.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

	public DtoGalleristCar getGalleristCarById(Long id);

	public List<DtoGalleristCar> getAllGalleristCars();

	public DtoGalleristCar updateGalleristCar(Long id, DtoGalleristCarIU dtoGalleristCarIU);

	public void deleteGalleristCar(Long id);

}
