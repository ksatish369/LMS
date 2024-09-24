package com.te.lms.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MaritalStatus {

	SINGLE("SINGLE"),MARRIED("MARRIED");
	
	private final String maritalStatus;
}
