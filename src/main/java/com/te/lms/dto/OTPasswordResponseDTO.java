package com.te.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OTPasswordResponseDTO {

	public OTPasswordResponseDTO(String string, String otpMessage) {
		// TODO Auto-generated constructor stub
	}

	private String message;
}
