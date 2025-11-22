package com.sametkaya_finch.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
//insert update icin bu dto, request icin alinacak
public class DtoAddressIU {

	@NotEmpty
	private String cityString;

	@NotEmpty
	private String districtString;

	@NotEmpty
	private String neighborhood;

	@NotEmpty
	private String street;
}
