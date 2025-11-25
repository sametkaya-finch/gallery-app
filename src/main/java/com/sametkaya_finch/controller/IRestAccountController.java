package com.sametkaya_finch.controller;

import java.util.List;

import com.sametkaya_finch.dto.DtoAccount;
import com.sametkaya_finch.dto.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

	public RootEntity<DtoAccount> getAccountById(Long id);

	public RootEntity<List<DtoAccount>> getAllAccount();

	public RootEntity<DtoAccount> updateAccount(Long id, DtoAccountIU dtoAccountIU);

	public RootEntity<String> deleteAccount(Long id);

}
