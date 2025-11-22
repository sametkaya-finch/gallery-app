package com.sametkaya_finch.dto;

import lombok.Data;

@Data
public class DtoGallerist extends DtoBase {

	private String firstName;

	private String lastName;

	private DtoAddress address;

}
