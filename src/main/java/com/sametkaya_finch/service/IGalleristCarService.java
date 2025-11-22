package com.sametkaya_finch.service;

import com.sametkaya_finch.dto.DtoGalleristCar;
import com.sametkaya_finch.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
