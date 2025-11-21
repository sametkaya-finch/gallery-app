package com.sametkaya_finch.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	NO_RECORD_EXIST("1004", "Kayit Bulunamadi!"), GENERAL_EXCEPTION("9999", "Genel Bir Hata Olustu!"),
	TOKEN_IS_EXPIRED("1005", "Token Suresi Bitmistir!"), USERNAME_NOT_FOUND("1006", "Username Bulunamadi!"),
	USERNAME_OR_PASSWORD_INVALID("1007", "Kullanici Adi veya Sifre Hatali!"),
	REFRESH_TOKEN_NOT_FOUND("1008", "Refresh Token Bulunamadi!"),
	REFRESH_TOKEN_IS_EXPIRED("1009", "Refresh Tokenin Suresi Bitmistir!");
	// Buradaki degerler conts icine parametre olarak gecilecek const icinde de
	// bunlar code ve message icine yazilacak

	private String code;

	private String message;

	MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
