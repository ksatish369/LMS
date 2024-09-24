package com.te.lms.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MockNumber {

	MOCK1("MOCK_1"),MOCK2("MOCK_2"),MOCK3("MOCK_3"),MOCK4("MOCK_4"),MOCK5("MOCK_5");

	private final String mockNumber;
	
}
