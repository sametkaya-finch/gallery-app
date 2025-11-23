package com.sametkaya_finch.service;

import com.sametkaya_finch.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);

}
