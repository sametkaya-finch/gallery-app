package com.sametkaya_finch.controller;

import com.sametkaya_finch.dto.DtoGallerist;
import com.sametkaya_finch.dto.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

}
