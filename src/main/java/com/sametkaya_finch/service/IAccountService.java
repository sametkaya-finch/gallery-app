package com.sametkaya_finch.service;

import java.util.List;

import com.sametkaya_finch.dto.DtoAccount;
import com.sametkaya_finch.dto.DtoAccountIU;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);

	public DtoAccount getAccountById(Long id);

	public List<DtoAccount> getAllAccounts();

	public DtoAccount updateAccount(Long id, DtoAccountIU dtoAccountIU);

	public void deleteAccount(Long id);

}
