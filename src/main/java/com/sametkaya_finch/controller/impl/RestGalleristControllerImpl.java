package com.sametkaya_finch.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sametkaya_finch.controller.IRestGalleristController;
import com.sametkaya_finch.controller.RestBaseController;
import com.sametkaya_finch.controller.RootEntity;
import com.sametkaya_finch.dto.DtoGallerist;
import com.sametkaya_finch.dto.DtoGalleristIU;
import com.sametkaya_finch.service.IGalleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController {

	@Autowired
	private IGalleristService galleristService;

	@Override
	@PostMapping("/save")
	public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {

		return ok(galleristService.saveGallerist(dtoGalleristIU));

	}

}
