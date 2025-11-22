package com.sametkaya_finch.controller;

import com.sametkaya_finch.dto.DtoGalleristCar;
import com.sametkaya_finch.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);

}
