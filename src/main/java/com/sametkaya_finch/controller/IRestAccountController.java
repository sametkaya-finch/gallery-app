package com.sametkaya_finch.controller;

import com.sametkaya_finch.dto.DtoAccount;
import com.sametkaya_finch.dto.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);

}
