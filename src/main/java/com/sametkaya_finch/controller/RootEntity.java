package com.sametkaya_finch.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
//null degerler geriye donmeyecek
@JsonInclude(value = Include.NON_NULL)
public class RootEntity<E> {

	private Integer status;

	private E payload;

	private String errorMessage;

	public static <E> RootEntity<E> ok(E payload) {
		RootEntity<E> rootEntity = new RootEntity<>();
		rootEntity.setStatus(200);
		rootEntity.setPayload(payload);
		rootEntity.setErrorMessage(null);

		return rootEntity;
	}

	public static <E> RootEntity<E> error(String errorMessage) {
		RootEntity<E> rootEntity = new RootEntity<>();
		rootEntity.setStatus(500);
		rootEntity.setPayload(null);
		rootEntity.setErrorMessage(errorMessage);

		return rootEntity;
	}

}
