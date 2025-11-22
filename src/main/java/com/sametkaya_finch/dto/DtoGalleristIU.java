package com.sametkaya_finch.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoGalleristIU {

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private Long addressId;

}
