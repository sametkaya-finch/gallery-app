package com.sametkaya_finch.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//component yani beani olusacak, 
//her istek geldiginde buraya dusecek

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		if (header == null) {
			filterChain.doFilter(request, response); // token yok geri kalan jwt dogrulama kodunu calistirma, bir
														// sonraki katmana gec
			return;
		}
		String token;
		String username;

		token = header.substring(7); // tokeni al

		try {
			username = jwtService.getUsernameByToken(token); // tokendan usernamei al

			// kullanici eger dogrulanmissa securitycontexholder icindedir daha bakmaya
			// gerek yok

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				// kullanicinin rollerini, sifresini vs dbden aldi alma islemini
				// userdetailsservice yapar
				// bu bilgiler userdetails ile tutulur

				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				// boyle bir kullanii var ve token suresi gecerli
				// yani giris yetkisi var auth olarak isaretlenebilir

				if (userDetails != null && jwtService.isTokenValid(token)) {

					// istegin kullanici adini, kullanici sifresi ama dogrulamada gerek yok null,
					// kullanicinin rolleri yetkileri
					// dogrulama tokeninin icine koyuldu

					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							username, null, userDetails.getAuthorities());

					// burasi zorunlu degil ama ileride daha detayli kullanici bilgisine ulasmak
					// istersen
					// token icine bunu da set et

					authenticationToken.setDetails(userDetails);

					// bu istegi kaydet, authenticated user

					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}

			}

		} catch (ExpiredJwtException ex) {
			throw new BaseException(new ErrorMessage(MessageType.TOKEN_IS_EXPIRED, ex.getMessage()));

		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
		}
		// sureci devam ettir
		filterChain.doFilter(request, response);

	}

}
