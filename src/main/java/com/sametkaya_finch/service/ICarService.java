package com.sametkaya_finch.service;

import com.sametkaya_finch.dto.DtoCar;
import com.sametkaya_finch.dto.DtoCarIU;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU dtoCarIU);
}
