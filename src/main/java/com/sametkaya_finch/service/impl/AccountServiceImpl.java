package com.sametkaya_finch.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.dto.DtoAccount;
import com.sametkaya_finch.dto.DtoAccountIU;
import com.sametkaya_finch.entity.Account;
import com.sametkaya_finch.repository.AccountRepository;
import com.sametkaya_finch.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	private Account createAccount(DtoAccountIU dtoAccountIU) {

		Account account = new Account();
		account.setCreateTimeDate(new Date());

		BeanUtils.copyProperties(dtoAccountIU, account);

		return account;
	}

	@Override
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
		Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));

		DtoAccount dtoAccount = new DtoAccount();
		BeanUtils.copyProperties(savedAccount, dtoAccount);

		return dtoAccount;
	}

}
