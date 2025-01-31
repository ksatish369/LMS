package com.te.lms.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class TwilioRouterConfig {

	@Autowired
	private TwilioOTPHandler twilioOTPHandler;

	@Bean
	public RouterFunction<ServerResponse> handleSMS() {
		return RouterFunctions.route().POST("/router/sendOTP",twilioOTPHandler::sendOTP)
				.POST("/router/validateOTP", twilioOTPHandler::validateOTP).build();
	}
}
