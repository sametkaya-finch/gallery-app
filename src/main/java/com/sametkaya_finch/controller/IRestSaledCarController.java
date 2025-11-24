package com.sametkaya_finch.controller;

import com.sametkaya_finch.dto.DtoSaledCar;
import com.sametkaya_finch.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
