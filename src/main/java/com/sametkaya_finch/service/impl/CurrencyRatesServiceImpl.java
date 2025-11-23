package com.sametkaya_finch.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sametkaya_finch.dto.CurrencyRatesResponse;
import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;
import com.sametkaya_finch.service.ICurrencyRatesService;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {

// https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A&startDate=01-02-2005&endDate=01-02-2017&type=json

	@Override
	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {

		String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
		String series = "TP.DK.USD.A";
		String type = "json";

		String endpoint = rootURL + "series=" + series + "&startDate=" + startDate + "&endDate" + endDate + "&type="
				+ type;

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("key", "AGQ4axhJUM");

		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

		// simdi istek atilabilir
		// endpointe istek atacaksin, get istegi, header, geri donus tipi

		try {
			RestTemplate restTemplate = new RestTemplate();

			ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
					new ParameterizedTypeReference<CurrencyRatesResponse>() {
					});

			if (response.getStatusCode().is2xxSuccessful()) {

				// CurrencyRatesResponse doner
				return response.getBody();

			}

		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
		}

		// try catch girmeden hata olursa null donsun
		return null;

	}

}
