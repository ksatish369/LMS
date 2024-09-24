package com.te.lms.dto;

import lombok.Data;

@Data
public class OTPasswordRequestDTO {

	private String number;
	private String username;
	private String otp;
}
