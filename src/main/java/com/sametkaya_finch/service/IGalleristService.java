package com.sametkaya_finch.service;

import java.util.List;

import com.sametkaya_finch.dto.DtoGallerist;
import com.sametkaya_finch.dto.DtoGalleristIU;

public interface IGalleristService {

	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);

	public DtoGallerist getGalleristById(Long id);

	public List<DtoGallerist> getAllGallerists();

	public DtoGallerist updateGallerist(Long id, DtoGalleristIU dtoGalleristIU);

	public void deleteGallerist(Long id);

}
