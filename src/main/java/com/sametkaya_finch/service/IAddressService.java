package com.sametkaya_finch.service;

import java.util.List;

import com.sametkaya_finch.dto.DtoAddress;
import com.sametkaya_finch.dto.DtoAddressIU;

public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

	public DtoAddress getAddressById(Long id);

	public List<DtoAddress> getAllAddresses();

	public DtoAddress uptadeAddress(Long id, DtoAddressIU dtoAddressIU);

	public void deleteAddress(Long id);

}
