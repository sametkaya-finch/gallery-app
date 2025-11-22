package com.sametkaya_finch.controller;

import com.sametkaya_finch.dto.DtoAddress;
import com.sametkaya_finch.dto.DtoAddressIU;

public interface IRestAddressController {

	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
