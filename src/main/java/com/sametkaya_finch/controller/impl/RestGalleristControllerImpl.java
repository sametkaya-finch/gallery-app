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

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoGallerist> getGalleristById(@PathVariable Long id) {
		return ok(galleristService.getGalleristById(id));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoGallerist>> getAllGallerists() {
		return ok(galleristService.getAllGallerists());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoGallerist> updateGallerist(@PathVariable Long id,
			@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
		return ok(galleristService.updateGallerist(id, dtoGalleristIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteGallerist(@PathVariable Long id) {
		galleristService.deleteGallerist(id);
		return ok("Gallerist Basariyla Silindi.");
	}

}
