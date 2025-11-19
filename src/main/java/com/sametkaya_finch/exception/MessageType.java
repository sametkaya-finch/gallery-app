package com.sametkaya_finch.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	NO_RECORD_EXIST("1004", "Kayit Bulunamadi!"), GENERAL_EXCEPTION("9999", "Genel Bir Hata Olustu!");
	//Buradaki degerler conts icine parametre olarak gecilecek const icinde de bunlar code ve message icine yazilacak

	private String code;

	private String message;

	MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
