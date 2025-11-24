package com.sametkaya_finch.dto;

import lombok.Data;

@Data
public class DtoSaledCar extends DtoBase {

	private DtoCustomer customer;

	private DtoGallerist gallerist;

	private DtoCar car;
}
