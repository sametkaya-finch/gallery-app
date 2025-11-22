package com.sametkaya_finch.service;

import com.sametkaya_finch.dto.DtoGallerist;
import com.sametkaya_finch.dto.DtoGalleristIU;

public interface IGalleristService {

	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);

}
