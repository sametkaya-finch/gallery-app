package com.sametkaya_finch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CurrencyRatesItems {

	// disaridan Tarih diye bir alan gelecek onu date ile maple
	// donerken de bu isimlerle doner
	@JsonProperty("Tarih")
	private String date;

	@JsonProperty("TP_DK_USD_A")
	private String usd;

}
