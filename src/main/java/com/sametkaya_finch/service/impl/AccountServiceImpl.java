package com.sametkaya_finch.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.dto.DtoAccount;
import com.sametkaya_finch.dto.DtoAccountIU;
import com.sametkaya_finch.entity.Account;
import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;
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

	@Override
	public DtoAccount getAccountById(Long id) {
		Optional<Account> optAccount = accountRepository.findById(id);
		if (optAccount.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Account account = optAccount.get();
		DtoAccount dtoAccount = new DtoAccount();
		BeanUtils.copyProperties(account, dtoAccount);

		return dtoAccount;
	}

	@Override
	public List<DtoAccount> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		List<DtoAccount> dtoAccountsList = new ArrayList<>();

		for (Account account : accounts) {
			DtoAccount dtoAccount = new DtoAccount();
			BeanUtils.copyProperties(account, dtoAccount);
			dtoAccountsList.add(dtoAccount);
		}

		return dtoAccountsList;
	}

	@Override
	public DtoAccount updateAccount(Long id, DtoAccountIU dtoAccountIU) {
		Optional<Account> optAccount = accountRepository.findById(id);
		if (optAccount.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Account account = optAccount.get();
		BeanUtils.copyProperties(dtoAccountIU, account, "id", "CreateTimeDate");
		Account updatedAccount = accountRepository.save(account);

		DtoAccount dtoAccount = new DtoAccount();
		BeanUtils.copyProperties(updatedAccount, dtoAccount);

		return dtoAccount;
	}

	@Override
	public void deleteAccount(Long id) {
		Optional<Account> optAccount = accountRepository.findById(id);
		if (optAccount.isEmpty()) {
			new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));

		}

		Account account = optAccount.get();

		accountRepository.delete(account);

	}

}
