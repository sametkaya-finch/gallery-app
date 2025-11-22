package com.sametkaya_finch.dto;

import lombok.Data;

@Data
public class DtoGalleristCar extends DtoBase {

	private DtoGallerist gallerist;

	private DtoCar car;
}
