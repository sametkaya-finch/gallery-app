package com.sametkaya_finch.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sametkaya_finch.controller.IRestGalleristCarController;
import com.sametkaya_finch.controller.RestBaseController;
import com.sametkaya_finch.controller.RootEntity;
import com.sametkaya_finch.dto.DtoGalleristCar;
import com.sametkaya_finch.dto.DtoGalleristCarIU;
import com.sametkaya_finch.service.IGalleristCarService;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {

	@Autowired
	private IGalleristCarService galleristCarService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

}
