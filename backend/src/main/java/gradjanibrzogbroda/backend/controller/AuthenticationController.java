package gradjanibrzogbroda.backend.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gradjanibrzogbroda.backend.domain.Zaposleni;
import gradjanibrzogbroda.backend.dto.JwtAuthenticationRequest;
import gradjanibrzogbroda.backend.dto.UserTokenState;
import gradjanibrzogbroda.backend.util.TokenUtils;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<Object> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
		
		System.out.println("========================================================");

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		Zaposleni zap = (Zaposleni) authentication.getPrincipal();
		
		if(zap.getLastPasswordResetDate()!=null) {
			return new ResponseEntity<>("User disabled",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		String jwt = tokenUtils.generateToken(zap);
		Long expiresIn = (long) tokenUtils.getExpiredIn();

		return new ResponseEntity<>(new UserTokenState(jwt, zap.getUsername(), zap.getRoles().get(0).getRole(), expiresIn),HttpStatus.OK);
	}

}
