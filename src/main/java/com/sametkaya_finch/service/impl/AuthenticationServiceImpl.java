package com.sametkaya_finch.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.dto.AuthRequest;
import com.sametkaya_finch.dto.AuthResponse;
import com.sametkaya_finch.dto.DtoUser;
import com.sametkaya_finch.dto.RefreshTokenRequest;
import com.sametkaya_finch.entity.RefreshToken;
import com.sametkaya_finch.entity.User;
import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;
import com.sametkaya_finch.jwt.JWTService;
import com.sametkaya_finch.repository.RefreshTokenRepository;
import com.sametkaya_finch.repository.UserRepository;
import com.sametkaya_finch.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	// Kayit olmak isteyen kullanici username ve password gonderecek
	// sifreyi kriptolayip saklayacagiz
	// kayit islemi yapilacak

	private User createUser(AuthRequest input) {
		User user = new User();
		user.setCreateTimeDate(new Date());
		user.setUsername(input.getUsername());
		user.setPassword(passwordEncoder.encode(input.getPassword()));

		return user;
	}

	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreateTimeDate(new Date()); // 1s 1dk 1s 4s
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		// token rastgele bir sey olacak
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);

		return refreshToken;
	}

	// user burada kaydediliyor
	// istegi atana dto donulecek

	@Override
	public DtoUser register(AuthRequest input) {
		DtoUser dtoUser = new DtoUser();

		User user = createUser(input);
		User savedUser = userRepository.save(user);

		BeanUtils.copyProperties(savedUser, dtoUser);

		return dtoUser;
	}

	@Override
	public AuthResponse authenticate(AuthRequest input) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					input.getUsername(), input.getPassword());

			// kullanici adi ve sifreyi kontrol edecek sorun yoksa devam varsa catch
			// devam kisminda da token oluscak

			authenticationProvider.authenticate(authenticationToken);

			// o kullaniciyi dbden cek ve genratetokena ver
			Optional<User> optUser = userRepository.findByUserName(input.getUsername());

			// token olustu bir de refreshtoken lazim
			String accessToken = jwtService.generateToken(optUser.get());

			// refresh token alindi dbye save edilmeye hazir
			RefreshToken refreshToken = createRefreshToken(optUser.get());

			// dbye save edildi
			RefreshToken savedResRefreshToken = refreshTokenRepository.save(refreshToken);

			return new AuthResponse(accessToken, savedResRefreshToken.getRefreshToken());

			// kisayoldan soyle de yazilabilirdi
			// refreshTokenRepository.save(createRefreshToken(optUser.get()));

		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));

		}
	}

	// su anin tarihinden onceyse true yani gecerli

	private boolean isValidRefreshToken(Date expiredDate) {
		return new Date().before(expiredDate);
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest input) {

		// boyle bir refreshToken var mi dbden kontrol edilir
		Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());

		// bulunamadi
		if (optRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));
		}

		// bulundu ama suresi gecmis
		if (!isValidRefreshToken(optRefreshToken.get().getExpiredDate())) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, input.getRefreshToken()));

		}

		// o refreshtokenin bagli oldugu user alindi
		User user = optRefreshToken.get().getUser();

		// bu user ile accesstoken olustur
		String accessToken = jwtService.generateToken(user);

		// bu user ile refreshtoken olustur
		RefreshToken refreshToken = createRefreshToken(user);

		// bu refresh tokeni dbye kaydet
		RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);

		// soyle de yazilabilirdi refreshTokenRepository.save(createRefreshToken(user));

		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}

}
