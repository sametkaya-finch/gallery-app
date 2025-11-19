package com.sametkaya_finch.entity;

import java.math.BigDecimal;

import com.sametkaya_finch.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

	@Column(name = "account_name")
	private String accountNoString;

	@Column(name = "iban")
	private String iban;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "currency_type")
	@Enumerated(EnumType.STRING) // TL verirsen tabloya TL USD verirsen USD yansir
	private CurrencyType currencyType;
}
