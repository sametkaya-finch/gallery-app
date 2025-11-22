package com.sametkaya_finch.controller;

import com.sametkaya_finch.dto.DtoCustomer;
import com.sametkaya_finch.dto.DtoCustomerIU;

public interface IRestCustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
