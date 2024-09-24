package com.te.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class PasswordDTO {

	private String employeeId;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
}
