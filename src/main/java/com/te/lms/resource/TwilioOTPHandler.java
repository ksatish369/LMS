package com.te.lms.resource;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import com.te.lms.dto.OTPasswordRequestDTO;
import com.te.lms.dto.OTPasswordResponseDTO;
import com.te.lms.service.TwilioOTPService;

@Component
public class TwilioOTPHandler {

	@Autowired
	private TwilioOTPService twilioOTPService;

	public ServerResponse sendOTP(ServerRequest serverRequest) throws Exception, IOException {
		System.err.println("Service Layer!!");
		OTPasswordRequestDTO otPasswordRequestDTO = serverRequest.body(OTPasswordRequestDTO.class);
		System.err.println("Service Layer!!  "+otPasswordRequestDTO.getNumber());
		System.err.println("Service Layer!!  "+otPasswordRequestDTO.getUsername());
		OTPasswordResponseDTO otpForPasswordReset = twilioOTPService.sendOtpForPasswordReset(otPasswordRequestDTO);
		return ServerResponse.status(HttpStatus.OK).body(otpForPasswordReset);
	}

	public ServerResponse validateOTP(ServerRequest serverRequest) throws Exception, IOException {
		OTPasswordRequestDTO otPasswordRequestDTO = serverRequest.body(OTPasswordRequestDTO.class);
		String validated = twilioOTPService.validateOTP(otPasswordRequestDTO.getOtp(),otPasswordRequestDTO.getUsername());
		return ServerResponse.status(HttpStatus.OK).body(validated);
	}
}
