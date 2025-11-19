package com.sametkaya_finch.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

	private MessageType messageType;

	private String ofStatic; // Static olarak setlemek istenen deger

	public String prepareErrorMessage() {
		StringBuilder builder = new StringBuilder();
		builder.append(messageType.getMessage());
		if (this.ofStatic != null) {
			builder.append(" : " + ofStatic);
		}
		return builder.toString();
	}

}
