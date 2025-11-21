package com.sametkaya_finch.service;

import com.sametkaya_finch.dto.AuthRequest;
import com.sametkaya_finch.dto.AuthResponse;
import com.sametkaya_finch.dto.DtoUser;
import com.sametkaya_finch.dto.RefreshTokenRequest;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest input);

	public AuthResponse authenticate(AuthRequest input);

	public AuthResponse refreshToken(RefreshTokenRequest input);

}
