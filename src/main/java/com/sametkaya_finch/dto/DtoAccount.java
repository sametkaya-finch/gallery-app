package com.sametkaya_finch.dto;

import java.math.BigDecimal;

import com.sametkaya_finch.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoAccount extends DtoBase {

	@NotNull
	private String accountNo;

	@NotNull
	private String iban;

	@NotNull
	private BigDecimal amount;

	@NotNull
	private CurrencyType currencyType;

}
