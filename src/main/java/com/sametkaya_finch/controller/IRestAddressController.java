package com.sametkaya_finch.controller;

import java.util.List;

import com.sametkaya_finch.dto.DtoAddress;
import com.sametkaya_finch.dto.DtoAddressIU;

public interface IRestAddressController {

	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);

	public RootEntity<DtoAddress> getAddressById(Long id);

	public RootEntity<List<DtoAddress>> getAllAddresses();

	public RootEntity<DtoAddress> updatedAddress(Long id, DtoAddressIU dtoAddressIU);

	public RootEntity<String> deleteAddress(Long id);
}
