package com.sametkaya_finch.service;

import com.sametkaya_finch.dto.DtoSaledCar;
import com.sametkaya_finch.dto.DtoSaledCarIU;

public interface ISaledCarService {

	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
