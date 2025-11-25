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

import com.sametkaya_finch.controller.IRestAddressController;
import com.sametkaya_finch.controller.RestBaseController;
import com.sametkaya_finch.controller.RootEntity;
import com.sametkaya_finch.dto.DtoAddress;
import com.sametkaya_finch.dto.DtoAddressIU;
import com.sametkaya_finch.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

	@Autowired
	private IAddressService addressService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.saveAddress(dtoAddressIU));
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoAddress> getAddressById(@PathVariable Long id) {
		return ok(addressService.getAddressById(id));
	}

	@GetMapping("list")
	@Override
	public RootEntity<List<DtoAddress>> getAllAddresses() {
		return ok(addressService.getAllAddresses());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoAddress> updatedAddress(@PathVariable Long id, @Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.uptadeAddress(id, dtoAddressIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteAddress(@PathVariable Long id) {
		addressService.deleteAddress(id);
		return ok("Address Basariyla Silindi.");
	}

}
