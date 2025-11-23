package com.sametkaya_finch.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sametkaya_finch.controller.IRestCurrencyRatesController;
import com.sametkaya_finch.controller.RestBaseController;
import com.sametkaya_finch.controller.RootEntity;
import com.sametkaya_finch.dto.CurrencyRatesResponse;
import com.sametkaya_finch.service.ICurrencyRatesService;

@RestController
@RequestMapping("/rest/api/")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController {

	@Autowired
	private ICurrencyRatesService currencyRatesService;

	@GetMapping("/currency-rates")
	@Override
	public RootEntity<CurrencyRatesResponse> getCurrenyRates(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		return ok(currencyRatesService.getCurrencyRates(startDate, endDate));

	}

}
