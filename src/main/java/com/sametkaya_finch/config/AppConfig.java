package com.sametkaya_finch.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.sametkaya_finch.entity.User;
import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;
import com.sametkaya_finch.repository.UserRepository;

@Configuration
public class AppConfig {

	@Autowired
	private UserRepository userRepository;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Optional<User> optional = userRepository.findByUserName(username);
				if (optional.isEmpty()) {
					throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, username));
				}
				return optional.get();
			}
		};
	}

	// Asil dogrulamayi yapan AuthenticationProviderdir
	// useri dbden bul sifre gecerli mi

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	// kendisine verilen Authentication nesnesini (username + password) uygun
	// AuthenticationProvidera yonlendirir
	// bu giris istegini hangi provider dogrulayacak

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	// BcryptPasswordEncoder nesnesini uretir
	// Bu nesne icerisinde sifreleri hashleyen metot bulunur

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
