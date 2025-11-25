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

import com.sametkaya_finch.controller.IRestAccountController;
import com.sametkaya_finch.controller.RestBaseController;
import com.sametkaya_finch.controller.RootEntity;
import com.sametkaya_finch.dto.DtoAccount;
import com.sametkaya_finch.dto.DtoAccountIU;
import com.sametkaya_finch.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

	@Autowired
	private IAccountService accountService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {

		return ok(accountService.saveAccount(dtoAccountIU));
	}

	@GetMapping("/list/{id}")
	@Override
	public RootEntity<DtoAccount> getAccountById(@PathVariable Long id) {
		return ok(accountService.getAccountById(id));
	}

	@GetMapping("/list")
	@Override
	public RootEntity<List<DtoAccount>> getAllAccount() {
		return ok(accountService.getAllAccounts());
	}

	@PutMapping("/update/{id}")
	@Override
	public RootEntity<DtoAccount> updateAccount(@PathVariable Long id, @Valid @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.updateAccount(id, dtoAccountIU));
	}

	@DeleteMapping
	@Override
	public RootEntity<String> deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ok("Account Silme Basarili.");
	}

}
