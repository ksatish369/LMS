package com.te.lms.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Designation {

	JUNIOR_DEVELOPER("JUNIOR_DEVELOPER"), SENIOR_DEVELOPER("SENIOR_DEVELOPER");
	
	private final String designation;
}
