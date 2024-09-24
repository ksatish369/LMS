package com.te.lms.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BatchTechnology {

	MEAN("MEAN"),MERN("MERN"),JAVA("JAVA");
	
	private final String Technology;
}
