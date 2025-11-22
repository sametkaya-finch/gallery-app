package com.sametkaya_finch.service;

import com.sametkaya_finch.dto.DtoCustomer;
import com.sametkaya_finch.dto.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

}
