package com.te.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.LoginDTO;
import com.te.lms.response.SuccessResponse;
import com.te.lms.security.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class LmsController {

	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;

	@PostMapping(path = "/login")
	public ResponseEntity<SuccessResponse<String>> login(@RequestBody LoginDTO loginDTO) {
		System.out.println("controllerss");
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		return ResponseEntity.<SuccessResponse<String>>ok(SuccessResponse.<String>builder().data("Welcome "+loginDTO.getUsername()).token(jwtUtils.generateToken(loginDTO.getUsername()))
				.message("Login Successful!!").build());
	}
	
	
}
