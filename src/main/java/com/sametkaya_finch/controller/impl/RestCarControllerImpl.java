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

import com.sametkaya_finch.controller.IRestCarController;
import com.sametkaya_finch.controller.RestBaseController;
import com.sametkaya_finch.controller.RootEntity;
import com.sametkaya_finch.dto.DtoCar;
import com.sametkaya_finch.dto.DtoCarIU;
import com.sametkaya_finch.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

	@Autowired
	private ICarService carService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.saveCar(dtoCarIU));
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoCar> getCarById(@PathVariable Long id) {
		return ok(carService.getCarById(id));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoCar>> getAllCars() {
		return ok(carService.getAllCars());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCar> updateCar(@PathVariable Long id, @Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.updateCar(id, dtoCarIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteCar(Long id) {
		carService.deleteCar(id);
		return ok("Car Basariyla Silindi.");
	}

}
