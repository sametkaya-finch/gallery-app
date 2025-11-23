package com.sametkaya_finch.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrencyRatesResponse {

	// burada jsonproperty gerek yok zaten ayni isimle mapledik

	private Integer totalCount;

	private List<CurrencyRatesItems> items;

}
