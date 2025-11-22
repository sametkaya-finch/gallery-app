package com.sametkaya_finch.dto;

import java.math.BigDecimal;

import com.sametkaya_finch.enums.CarStatusType;
import com.sametkaya_finch.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoCarIU {

	@NotNull
	private String plaka;

	@NotNull
	private String brand;

	@NotNull
	private String model;

	@NotNull
	private Integer productionYear;

	@NotNull
	private BigDecimal price;

	@NotNull
	private CurrencyType currencyType;

	@NotNull
	private BigDecimal damagePrice;

	@NotNull
	private CarStatusType carStatusType;

}
