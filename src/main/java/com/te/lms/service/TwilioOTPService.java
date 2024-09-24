package com.te.lms.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.lms.config.TwilioConfig;
import com.te.lms.dto.OTPasswordRequestDTO;
import com.te.lms.dto.OTPasswordResponseDTO;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioOTPService {

	@Autowired
	private TwilioConfig twilioConfig;
	private Map<String, String> otpMap = new HashMap<>();

	public OTPasswordResponseDTO sendOtpForPasswordReset(OTPasswordRequestDTO passwordRequestDTO) {

		OTPasswordResponseDTO otPasswordResponseDTO = null;
		try {
			PhoneNumber to = new PhoneNumber(passwordRequestDTO.getNumber());
			PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
			String otp = generateOTP();
			String otpMessage = "Dear Customer , Your OTP is " + otp
					+ ". Use this passcode to complete your transaction!!";
			System.err.println("Message is getting created!!");
			Message message = Message.creator(to,from,otpMessage).create();
			System.err.println("Message created!!");
			otpMap.put(passwordRequestDTO.getUsername(), otp);
			otPasswordResponseDTO = new OTPasswordResponseDTO("Delivered", otpMessage);
		} catch (Exception e) {
			otPasswordResponseDTO = new OTPasswordResponseDTO("Failed", e.getMessage());
		}
		System.err.println(twilioConfig.getAccountSid());
		System.err.println(twilioConfig.getAuthToken());

		return otPasswordResponseDTO;
	}

	public String validateOTP(String userInputOTP, String userName) {
		if (userInputOTP.equals(otpMap.get(userName))) {
			return "Validation Successfull!!";
		} else {
			throw new IllegalArgumentException("Invalid OTP!!Please retry...");
		}
	}

	public String generateOTP() {
		return new DecimalFormat("000000").format(new Random().nextInt(999999));
	}
}
