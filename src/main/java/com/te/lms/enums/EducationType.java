package com.te.lms.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EducationType {

	SSLC("SS"),DIPLOMA("DIPLOMA"),UG("UG"),PG("PG");
	
	private final String educationType;
}
