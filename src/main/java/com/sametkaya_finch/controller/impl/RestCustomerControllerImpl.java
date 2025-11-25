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

import com.sametkaya_finch.controller.IRestCustomerController;
import com.sametkaya_finch.controller.RestBaseController;
import com.sametkaya_finch.controller.RootEntity;
import com.sametkaya_finch.dto.DtoCustomer;
import com.sametkaya_finch.dto.DtoCustomerIU;
import com.sametkaya_finch.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {

	@Autowired
	private ICustomerService customerService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoCustomer> getCustomerById(@PathVariable Long id) {
		return ok(customerService.getCustomerById(id));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoCustomer>> getAllCustomers() {
		return ok(customerService.getAllCustomers());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoCustomer> updateCustomer(@PathVariable Long id,
			@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
		return ok(customerService.updateCustomer(id, dtoCustomerIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ok("Customer Basariyla Silindi.");
	}

}
