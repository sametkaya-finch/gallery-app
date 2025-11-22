package com.sametkaya_finch.dto;

import java.math.BigDecimal;

import com.sametkaya_finch.enums.CarStatusType;
import com.sametkaya_finch.enums.CurrencyType;

import lombok.Data;

@Data
public class DtoCar extends DtoBase {

	private String plaka;

	private String brand;

	private String model;

	private Integer productionYear;

	private BigDecimal price;

	private CurrencyType currencyType;

	private BigDecimal damagePrice;

	private CarStatusType carStatusType;

}
