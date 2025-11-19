package com.sametkaya_finch.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.sametkaya_finch.exception.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {

	// BaseException geldiginde onu yakala

	@ExceptionHandler(value = { BaseException.class })
	public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request) {
		return ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
	}

	// validationdan hata geldiginde onu yakala
	// isteğe ait ekstra bilgiler (path, headers vb.) request icinde

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex, WebRequest request) {

		// Bir fieldin birden fazla mesaji olabilir bu yuzden value list ornegin email
		// icin gecersiz format, bos olamaz vs

		Map<String, List<String>> map = new HashMap<>();

		// Exception icindeki BindingResult uzerinden tum validation hatalarini
		// (ObjectError) aliyoruz

		for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {

			// ObjectError genel bir tip, biz burada field bazli hatayi almak istiyoruz ve
			// filed adini aliyoruz

			String fieldName = ((FieldError) objectError).getField();

			// Eğer map icinde bu field icin daha once bir giris varsa

			if (map.containsKey(fieldName)) {

				// O field'a ait mevcut listeyi aliyoruz ve yeni hata mesajını ekleyip tekrar
				// mape koyuyoruz

				map.put(fieldName, addValue(map.get(fieldName), objectError.getDefaultMessage()));

			} else {

				// Eğer bu field icin daha önce hic hata eklenmemisse
				// yeni bir liste olusturuyoruz, ilk hata mesajını bu listeye ekleyip map'e
				// koyuyoruz.

				map.put(fieldName, addValue(new ArrayList<>(), objectError.getDefaultMessage()));
			}
		}

		return ResponseEntity.badRequest().body(createApiError(map, request));
	}

	// Verilen listeye (list) yeni bir deger (newValue) ekleyip
	// güncellenmis listeyi geri donen yardimci metot.

	private List<String> addValue(List<String> list, String newValue) {
		list.add(newValue); // listeye yeni hata mesajini ekliyoruz
		return list;
	}

	private String getHostName() {
		try {
			return Inet4Address.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}

	public <E> ApiError<E> createApiError(E message, WebRequest request) {
		ApiError<E> apiError = new ApiError<>();
		apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		Exception exception = new Exception();
		exception.setPath(request.getDescription(false).substring(4)); // uri= almamak icin
		exception.setCreateTime(new Date());
		exception.setMessage(message);
		exception.setHostName(getHostName());

		apiError.setException(exception);

		return apiError;
	}

}
