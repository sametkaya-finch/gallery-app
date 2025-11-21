package com.sametkaya_finch.controller;

public class RestBaseController {

	public <E> RootEntity<E> ok(E payload) {
		return RootEntity.ok(payload);
	}

	public <E> RootEntity<E> error(String errorMessage) {
		return RootEntity.error(errorMessage);
	}
}
