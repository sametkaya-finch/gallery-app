package com.sametkaya_finch.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sametkaya_finch.controller.IRestGalleristCarController;
import com.sametkaya_finch.controller.RestBaseController;
import com.sametkaya_finch.controller.RootEntity;
import com.sametkaya_finch.dto.DtoGalleristCar;
import com.sametkaya_finch.dto.DtoGalleristCarIU;
import com.sametkaya_finch.service.IGalleristCarService;

import jakarta.validation.Valid;

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

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoGalleristCar> getGalleristCarById(Long id) {
		return ok(galleristCarService.getGalleristCarById(id));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoGalleristCar>> getAllGalleristCars() {
		return ok(galleristCarService.getAllGalleristCars());

	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoGalleristCar> updateGalleristCar(@PathVariable Long id,
			@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.updateGalleristCar(id, dtoGalleristCarIU));
	}

	@DeleteMapping
	@Override
	public RootEntity<String> deleteGalleristCar(@PathVariable Long id) {
		galleristCarService.deleteGalleristCar(id);
		return ok("GalleristCar Basariyla Silindi");
	}

}
