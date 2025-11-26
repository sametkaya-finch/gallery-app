package com.sametkaya_finch.controller;

import java.util.List;

import com.sametkaya_finch.dto.DtoGallerist;
import com.sametkaya_finch.dto.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);

	public RootEntity<DtoGallerist> getGalleristById(Long id);

	public RootEntity<List<DtoGallerist>> getAllGallerists();

	public RootEntity<DtoGallerist> updateGallerist(Long id, DtoGalleristIU dtoGalleristIU);

	public RootEntity<String> deleteGallerist(Long id);

}
