package com.sametkaya_finch.service;

import java.util.List;

import com.sametkaya_finch.dto.DtoCustomer;
import com.sametkaya_finch.dto.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);

	public DtoCustomer getCustomerById(Long id);

	public List<DtoCustomer> getAllCustomers();

	public DtoCustomer updateCustomer(Long id, DtoCustomerIU dtoCustomerIU);

	public void deleteCustomer(Long id);

}
