package com.sametkaya_finch.controller;

import com.sametkaya_finch.dto.DtoCar;
import com.sametkaya_finch.dto.DtoCarIU;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);

}
