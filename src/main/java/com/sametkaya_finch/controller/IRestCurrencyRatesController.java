package com.sametkaya_finch.controller;

import com.sametkaya_finch.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

	public RootEntity<CurrencyRatesResponse> getCurrenyRates(String startDate, String endDate);

}
