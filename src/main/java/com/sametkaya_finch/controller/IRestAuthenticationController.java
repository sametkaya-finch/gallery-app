package com.sametkaya_finch.controller;

import com.sametkaya_finch.dto.AuthRequest;
import com.sametkaya_finch.dto.AuthResponse;
import com.sametkaya_finch.dto.DtoUser;
import com.sametkaya_finch.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest input);

	public RootEntity<AuthResponse> authenticate(AuthRequest input);

	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);

}
