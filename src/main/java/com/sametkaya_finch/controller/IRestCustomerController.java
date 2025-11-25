package com.sametkaya_finch.controller;

import java.util.List;

import com.sametkaya_finch.dto.DtoCustomer;
import com.sametkaya_finch.dto.DtoCustomerIU;

public interface IRestCustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);

	public RootEntity<DtoCustomer> getCustomerById(Long id);

	public RootEntity<List<DtoCustomer>> getAllCustomers();

	public RootEntity<DtoCustomer> updateCustomer(Long id, DtoCustomerIU dtoCustomerIU);

	public RootEntity<String> deleteCustomer(Long id);
}
