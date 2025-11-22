package com.sametkaya_finch.dto;

import lombok.Data;

@Data
//geriye bu dto donulecek dtobase ve buradakilerle 6 field donulecek
public class DtoAddress extends DtoBase {

	private String cityString;

	private String districtString;

	private String neighborhood;

	private String street;

}
